package com.scando.learning.modules.student.service.impl;


import com.scando.learning.common.constants.Constants;
import com.scando.learning.common.constants.ErrorCodeEnum;
import com.scando.learning.common.constants.UserType;
import com.scando.learning.common.constants.VerifyType;
import com.scando.learning.common.dao.UserLoginInfoDao;
import com.scando.learning.common.exception.ServiceException;
import com.scando.learning.common.models.ArchiveClassEnroll;
import com.scando.learning.common.models.Otp;
import com.scando.learning.common.models.User;
import com.scando.learning.common.models.UserLoginInfo;
import com.scando.learning.common.models.rest.PagedData;
import com.scando.learning.common.models.rest.S3RequestDetails;
import com.scando.learning.common.utils.NotificationUtils;
import com.scando.learning.common.utils.ScandoUtils;
import com.scando.learning.common.utils.WebUtils;
import com.scando.learning.modules.auth.dao.S3Dao;
import com.scando.learning.modules.auth.dao.UserDao;
import com.scando.learning.modules.auth.utils.AuthUtils;
import com.scando.learning.modules.student.builder.StudentResponseBuilder;
import com.scando.learning.modules.student.dao.StudentDao;
import com.scando.learning.modules.student.models.rest.*;
import com.scando.learning.modules.student.service.StudentService;
import com.scando.learning.modules.teacher.dao.TeacherDao;
import com.scando.learning.modules.teacher.models.ClassEnroll;
import com.scando.learning.modules.teacher.models.ClassRoom;
import com.scando.learning.modules.teacher.models.ScheduledClass;
import com.scando.learning.modules.teacher.models.rest.EnrollDetails;
import com.scando.learning.modules.teacher.models.rest.GetTimeTableResponseModel;
import com.scando.learning.modules.teacher.utils.TimeTableUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    UserDao userDao;

    @Autowired
    private StudentResponseBuilder responseBuilder;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private S3Dao s3Dao;

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private UserLoginInfoDao userLoginInfoDao;

    @Autowired
    private NotificationUtils notificationUtils;


    @Override
    public StudentSignUpResponse studentSignUp(StudentSignUpRequest studentSignUpRequest) {
        if (ScandoUtils.checkAppId(studentSignUpRequest.getAppId())) {
            throw new ServiceException(ErrorCodeEnum.STUDENT_SIGNUP_INVALID_APP_ID);
        }
        User user = userDao.getUserByNumber(studentSignUpRequest.getNumber());
        if (null != user) {
            LOGGER.debug("Number already registered for userId {}", user.getUserId());
            throw new ServiceException(ErrorCodeEnum.STUDENT_SIGNUP_EXISTING_NUMBER);
        }
        Otp otp = userDao.getExistingOtp(studentSignUpRequest.getNumber());
        if (null == otp) {
            LOGGER.debug("Number not registered {}", studentSignUpRequest.getNumber());
            throw new ServiceException(ErrorCodeEnum.STUDENT_SIGNUP_NUMBER_NOT_REGISTERED);
        }
        if (otp.getStatus() == 1) {
            if (otp.getExpiryTime() < System.currentTimeMillis()) {
                LOGGER.debug("Verified otp expired for  {}", studentSignUpRequest.getNumber());
                throw new ServiceException(ErrorCodeEnum.STUDENT_SIGNUP_VERIFIED_OTP_EXPIRED);
            }
            String profileUrl = null;
            if (null != studentSignUpRequest.getProfileId() && 0 != studentSignUpRequest.getProfileId()) {
                S3RequestDetails s3RequestDetails = s3Dao.getByRequestId(studentSignUpRequest.getProfileId());
                if (null != s3RequestDetails) {
                    profileUrl = s3RequestDetails.getUrl();
                    s3Dao.delete(s3RequestDetails);
                }
            }
            user = userDao.save(User.builder()
                    .name(studentSignUpRequest.getUserName())
                    .phoneNumber(studentSignUpRequest.getNumber())
                    .userStatus(0)
                    .userType(UserType.STUDENT.getCode())
                    .profilePicUrl(profileUrl)
                    .build());
            userDao.deleteGeneratedOtp(otp);
            HashMap<String, String> token = authUtils.getJwtToken(otp.getPhoneNumber(),
                    UserType.STUDENT, user);
            UserLoginInfo userLoginInfo = UserLoginInfo.builder()
                    .userId(user.getUserId())
                    .appId(studentSignUpRequest.getAppId())
                    .build();

            userLoginInfo.setToken(token.get("token"));
            userLoginInfo.setRefreshToken(token.get("refreshToken"));
            userLoginInfo.setExpiryTime(Long.valueOf(token.get("expiry")));
            userLoginInfoDao.save(userLoginInfo);
            return responseBuilder.buildStudentSignupResponse(studentSignUpRequest.getAppId(), user.getUserId(),
                    user.getUserType(), token.get("token"), token.get("refreshToken"), profileUrl,
                    VerifyType.USER_LOGIN.getCode(), user.getName());

        } else {
            LOGGER.debug("Number not verified {}", studentSignUpRequest.getNumber());
            throw new ServiceException(ErrorCodeEnum.STUDENT_SIGNUP_NUMBER_NOT_VERIFIED);
        }
    }

    @Override
    @Transactional
    public EnrollClassResponse enrollClass(EnrollClassRequest enrollClassRequest) {

        ClassRoom classDetails = teacherDao.getClassRoomByClassId(enrollClassRequest.getClassId());
        if (classDetails == null) {
            LOGGER.error("Class not found with classId:{}", enrollClassRequest.getClassId());
            throw new ServiceException(ErrorCodeEnum.CLASSROOM_NOT_FOUND);
        }

        ClassEnroll enrollmentExists = studentDao.getEnrolledClassByClassIdAndStudentId(enrollClassRequest.getClassId(), WebUtils.getUserId());
        if (enrollmentExists != null) {
            LOGGER.error("Student already enrolled to the specific class with classId:{}", enrollmentExists.getClassId());
            throw new ServiceException(ErrorCodeEnum.STUDENT_ENROLL_CLASS_ALREADY_ENROLLED);
        }

        ClassEnroll classEnrollment = new ClassEnroll();
        classEnrollment.setClassId(enrollClassRequest.getClassId());
        classEnrollment.setTeacherId(classDetails.getTeacherId());
        classEnrollment.setStudentId(WebUtils.getUserId());
        classEnrollment.setEnrollStatus(0);
        classEnrollment.setEnrollTime(System.currentTimeMillis());
        classEnrollment = studentDao.enrollClass(classEnrollment);
        User user = userDao.getUser(WebUtils.getUserId());
        //send Notification
        notificationUtils.triggerNotification(classDetails.getTeacherId(),
                user.getName() + Constants.ENROLL_MESSAGE , Constants.ENROLL_TITLE);
        LOGGER.debug("Class Enrollment was successful with enrollmentId:{}", classEnrollment.getEnrollId());
        return responseBuilder.buildEnrollClassResponse(classEnrollment.getEnrollId(), "enrolled to class successfully");
    }

    @Override
    public CheckLiveClassResponse liveClassCheck(Long classId) {
        return null;
    }

    @Override
    public GetTimetableResponse getTimeTable(Long classRoomId) {
        Long userId = WebUtils.getUserId();
        ClassEnroll enrollmentExists = studentDao.getEnrolledClassByClassIdAndStudentId(classRoomId, userId);
        if(null == enrollmentExists) {
            throw new ServiceException(ErrorCodeEnum.ENROLLED_CLASS_GET_TIMETABLE_NOT_ENROLLED);
        }
        if(0 == enrollmentExists.getEnrollStatus())
        {
            throw new ServiceException(ErrorCodeEnum.ENROLLED_CLASS_GET_TIMETABLE_ENROLL_NOT_APPROVED);
        }

        List<ScheduledClass> scheduledClass = teacherDao.getTimetableByClassId(classRoomId);
        if (scheduledClass.isEmpty()) {
            throw new ServiceException(ErrorCodeEnum.DATA_NOT_FOUND);
        }
        return responseBuilder.buildGetTimeTableResponse(GetTimeTableResponseModel.builder()
                .classRoomId(classRoomId)
                .teacherId(scheduledClass.get(0).getTeacherId())
                .timeTable(TimeTableUtils.formatTimeTable(scheduledClass))
                .build());
    }

    @Override
    public GetStudentClassDetailResponse getClassDetail(Long classId) {
        return responseBuilder.buildGetStudentClassDetailResponse(studentDao.getStudentSpecificClassDetails(classId));
    }

    @Override
    public GetEnrolledClassListResponse getEnrolledClass(Long userId) {
        return null;
    }

    @Override
    @Transactional
    public UnEnrollResponse unEnrollClass(EnrollClassRequest enrollClassRequest) {

        ClassRoom classDetails = teacherDao.getClassRoomByClassId(enrollClassRequest.getClassId());
        if (classDetails == null) {
            LOGGER.error("Class not found with classId:{}", enrollClassRequest.getClassId());
            throw new ServiceException(ErrorCodeEnum.CLASSROOM_NOT_FOUND);
        }

        ClassEnroll enrollmentExists = studentDao.getEnrolledClassByClassIdAndStudentId(enrollClassRequest.getClassId(), WebUtils.getUserId());
        if (enrollmentExists == null) {
            LOGGER.error("Student not enrolled to the specific class with classId:{}", enrollClassRequest.getClassId());
            throw new ServiceException(ErrorCodeEnum.STUDENT_UN_ENROLL_CLASS_NOT_ENROLLED);
        }

        ArchiveClassEnroll archiveClassEnroll = new ArchiveClassEnroll();
        BeanUtils.copyProperties(enrollmentExists, archiveClassEnroll);
        studentDao.saveArchiveClassEnroll(archiveClassEnroll);
        studentDao.deleteClassEnroll(enrollmentExists);

        LOGGER.debug("Class UnEnrollment was successful with enrollmentId:{}", enrollmentExists.getEnrollId());
        return responseBuilder.buildUnEnrollClassResponse(enrollmentExists.getEnrollId(), "unEnrolled successfully");
    }

    @Override
    public GetStudentClassesResponse getStudentClassOnSpecificDay(Long userId, String day) {
        return responseBuilder.buildGetStudentClassesResponse(studentDao.getStudentClassOnSpecificDay(userId, day));
    }

    @Override
    public GetStudentClassDetailResponse getAllClassDetail(Long userId, Long isScheduled) {
        return responseBuilder.buildGetStudentClassDetailResponse(studentDao.getStudentAllClassDetails(userId, isScheduled));
    }

    @Override
    public GetEnrollListResponse getEnrollList(GetEnrollListRequest getEnrollListRequest) {
        Page<StudentEnrollDetails> enrollList = studentDao.getEnrollList(getEnrollListRequest);
        if (!enrollList.hasContent()) {
            throw new ServiceException(ErrorCodeEnum.DATA_NOT_FOUND);
        }
        PagedData<StudentEnrollDetails> pagedEnrollList = ScandoUtils.setPageResponse(enrollList);

        return responseBuilder.buildEnrollListResponse(pagedEnrollList);
    }
}
