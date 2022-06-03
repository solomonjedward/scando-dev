package com.scando.learning.modules.teacher.service.Impl;

import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.common.constants.Constants;
import com.scando.learning.common.constants.ErrorCodeEnum;
import com.scando.learning.common.constants.UserType;
import com.scando.learning.common.dao.UserLoginInfoDao;
import com.scando.learning.common.exception.ServiceException;
import com.scando.learning.common.models.ArchiveClassEnroll;
import com.scando.learning.common.models.User;
import com.scando.learning.common.models.UserLoginInfo;
import com.scando.learning.common.models.rest.NotificationPublishRequest;
import com.scando.learning.common.models.rest.PageDetails;
import com.scando.learning.common.models.rest.PagedData;
import com.scando.learning.common.service.WebFluxService;
import com.scando.learning.common.utils.NotificationUtils;
import com.scando.learning.common.utils.WebUtils;
import com.scando.learning.modules.auth.utils.AuthUtils;
import com.scando.learning.modules.student.dao.StudentDao;
import com.scando.learning.modules.teacher.builder.TeacherResponseBuilder;
import com.scando.learning.modules.teacher.dao.TeacherDao;
import com.scando.learning.modules.teacher.models.ClassEnroll;
import com.scando.learning.modules.teacher.models.ClassRoom;
import com.scando.learning.modules.teacher.models.ScheduledClass;
import com.scando.learning.modules.teacher.models.Subject;
import com.scando.learning.modules.teacher.models.rest.*;
import com.scando.learning.modules.teacher.service.TeacherService;
import com.scando.learning.modules.teacher.utils.TimeTableUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherDao teacherDao;

    @Autowired
    TeacherResponseBuilder teacherResponseBuilder;


    @Autowired
    private TeacherResponseBuilder responseBuilder;

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private UserLoginInfoDao userLoginInfoDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    WebFluxService webFluxService;

    @Autowired
    NotificationUtils notificationUtils;

    @Override
    @Transactional
    public TeacherSignUpResponse teacherSignUp(TeacherSignUpRequest teacherSignUpRequest) {
        User user = setUser(teacherSignUpRequest);
        user = teacherDao.save(user);
        LOGGER.debug("user details saved successfully with id:{}", user.getUserId());

        List<Subject> subjects = setSubjects(user.getUserId(), teacherSignUpRequest);
        teacherDao.save(subjects);
        LOGGER.debug("Subject detail saved successfully");

        HashMap<String, String> token = authUtils.getJwtToken(teacherSignUpRequest.getNumber(),
                UserType.STUDENT, user);

        UserLoginInfo userLoginInfo = new UserLoginInfo();
        userLoginInfo.setUserId(user.getUserId());
        userLoginInfo.setAppId(teacherSignUpRequest.getAppId());
        userLoginInfo.setToken(token.get("token"));
        userLoginInfo.setRefreshToken(token.get("refreshToken"));
        userLoginInfo.setExpiryTime(Long.valueOf(token.get("expiry")));
        userLoginInfoDao.save(userLoginInfo);

        return teacherResponseBuilder.buildTeacherSignUpResponse(teacherSignUpRequest, user, userLoginInfo, "User Created successfully");
    }

    @Override
    @Transactional
    public CreateClassRoomResponse createClassRoom(CreateClassRoomRequest createClassRoomRequest) {

        ClassRoom classRoom = new ClassRoom();
        classRoom.setClassName(createClassRoomRequest.getClassName());
        classRoom.setSubjectName(createClassRoomRequest.getSubjectName());
        classRoom.setStatus(0);
        classRoom.setTeacherId(WebUtils.getUserId());
        classRoom.setClassType(createClassRoomRequest.getClassType());
        if (createClassRoomRequest.isTimetableEnabled()) {
            classRoom.setIsScheduled(1);
        } else {
            classRoom.setIsScheduled(0);
        }

        classRoom = teacherDao.save(classRoom);
        LOGGER.debug("classRoom details saved successfully with id:{}", classRoom.getClassId());

        if (createClassRoomRequest.isTimetableEnabled()) {
            Integer repeat = 0;
            if (createClassRoomRequest.isRepeatEnabled()) {
                repeat = createClassRoomRequest.isRepeatEnabled() ? 1 : 0;
            }
            List<ScheduledClass> scheduledClasses = setTimeTable(createClassRoomRequest.getTimeTable(),
                    classRoom.getClassId(), WebUtils.getUserId(), repeat);
            List<ScheduledClass> savedClass = teacherDao.saveAll(scheduledClasses);
            LOGGER.debug("new class schedules saved successfully with total :{} sessions", savedClass.size());
        }

        return responseBuilder.createClassRoomResponse(classRoom.getClassId(), "Classroom created successfully");
    }

    @Override
    @Transactional
    public CreateClassRoomResponse editClassRoom(CreateClassRoomRequest editClassRequest, Long classId) {

        ClassRoom classRoom = teacherDao.getClassRoomByClassId(classId);
        if (classRoom == null) {
            LOGGER.error("Classroom with id:{} not found", classId);
            throw new ServiceException(ErrorCodeEnum.CLASSROOM_NOT_FOUND);
        }
        if (!Objects.equals(WebUtils.getUserId(), classRoom.getTeacherId())) {
            LOGGER.error("user with Id:{} doesn't have permission to edit class with Id:{}", WebUtils.getUserId(), classId);
            throw new ServiceException(ErrorCodeEnum.CLASSROOM_FORBIDDEN_ACCESS);
        }

        classRoom.setClassName(editClassRequest.getClassName());
        classRoom.setSubjectName(editClassRequest.getSubjectName());
        classRoom.setStatus(0);
        classRoom.setTeacherId(WebUtils.getUserId());
        classRoom.setClassType(editClassRequest.getClassType());
        if (editClassRequest.isTimetableEnabled()) {
            classRoom.setIsScheduled(1);
        } else {
            classRoom.setIsScheduled(0);
        }

        teacherDao.save(classRoom);
        LOGGER.debug("classRoom details updated successfully with id:{}", classId);

        if (editClassRequest.isTimetableEnabled()) {
            Integer repeat = 0;
            if (editClassRequest.isRepeatEnabled()) {
                repeat = editClassRequest.isRepeatEnabled() ? 1 : 0;
            }
            List<ScheduledClass> scheduledClasses = setTimeTable(editClassRequest.getTimeTable(),
                    classRoom.getClassId(), WebUtils.getUserId(), repeat);
            List<ScheduledClass> savedClass = teacherDao.saveAll(scheduledClasses);
            LOGGER.debug("new class schedules saved successfully with total :{} sessions", savedClass.size());
        } else {
            teacherDao.deleteScheduledClasses(classId);
            LOGGER.debug("deleting old schedules as new schedules disabled while updating classId: {}", classId);
        }

        return responseBuilder.createClassRoomResponse(classRoom.getClassId(), "Classroom updated successfully");
    }

    @Override
    @Transactional
    public DeleteClassRoomResponse deleteClassRoom(DeleteClassRoomRequest deleteClassRoomRequest) {
        Long classId = deleteClassRoomRequest.getClassId();
        ClassRoom classRoom = teacherDao.getClassRoomByClassId(classId);
        if (classRoom == null) {
            LOGGER.error("class with id:{} not found", classId);
            throw new ServiceException(ErrorCodeEnum.DELETE_CLASS_NOT_FOUND);
        }
        if (!Objects.equals(WebUtils.getUserId(), classRoom.getTeacherId())) {
            LOGGER.error("user with Id:{} doesn't have permission to delete class with Id:{}", WebUtils.getUserId(), classId);
            throw new ServiceException(ErrorCodeEnum.DELETE_CLASS_FORBIDDEN_ACCESS);
        }

        List<ClassEnroll> classEnrollList = teacherDao.getClassEnrollByClassId(classId);
        if (classEnrollList != null) {
            for (ClassEnroll classEnroll : classEnrollList) {
                ArchiveClassEnroll archiveClassEnroll = new ArchiveClassEnroll();
                BeanUtils.copyProperties(classEnroll, archiveClassEnroll);
                if (teacherDao.saveArchiveClassEnroll(archiveClassEnroll) != null) {
                    teacherDao.deleteClassEnrollById(classEnroll.getEnrollId());
                }
            }
        }

        teacherDao.deleteClassDoubtByClassId(classId);
        teacherDao.deleteClassVideoByClassId(classId);
        teacherDao.deleteStudyMaterialByClassId(classId);
        teacherDao.deleteScheduledClasses(classId);
        teacherDao.deleteClassRoom(classRoom);
        LOGGER.debug("Entries related with classId:{} has been deleted", classId);
        return responseBuilder.buildDeleteClassRoomResponse("ClassRoom deleted", 20001);
    }

    @Transactional
    @Override
    public CreateTimeTableResponse createTimeTable(CreateTimeTableRequest createTimeTableRequest) {
        Integer repeat = 0;
        ClassRoom classRoom = teacherDao.getClassRoomByClassId(createTimeTableRequest.getClassId());
        if(null == classRoom) {
            LOGGER.debug("classroom not exist {}" , createTimeTableRequest.getClassId());
            throw new ServiceException(ErrorCodeEnum.CREATE_TIME_TABLE_CLASSID_NOT_FOUND);

        }
        if (classRoom.getTeacherId().longValue() != createTimeTableRequest.getTeacherId().longValue()) {
            LOGGER.debug("Teacherid and classid mismatch teacherId : {} classId {}",
                    createTimeTableRequest.getTeacherId(), createTimeTableRequest.getClassId());
            throw new ServiceException(ErrorCodeEnum.CREATE_TIME_TABLE_CLASSID_NOT_MATCHING);
        }
        if (createTimeTableRequest.isRepeatEnabled()) {
            repeat = createTimeTableRequest.isRepeatEnabled() ? 1 : 0;
        }

        List<ScheduledClass> scheduledClasses = setTimeTable(createTimeTableRequest.getTimeTable(),
                createTimeTableRequest.getClassId(), createTimeTableRequest.getTeacherId(), repeat);
        List<ScheduledClass> savedClass = teacherDao.saveAll(scheduledClasses);
        NotificationPublishRequest notificationPublishRequest = NotificationPublishRequest.builder()
                        .title("Time table changed")
                        .message("Time table changed for class " + classRoom.getClassName())
                        .topic(classRoom.getClassId().toString())
                        .build();
        webFluxService.post(notificationPublishRequest, ApiUrls.PUBLISH_NOTIFICATION);
        return responseBuilder.buildCreateTimeTableResponse("Timetable saved successfully");

    }


    @Override
    @Transactional
    public ApproveClassResponse approveClass(ApproveClassRequest approveClassRequest) {
        ClassRoom classDetails = teacherDao.getClassRoomByClassId(approveClassRequest.getClassId());
        if (classDetails == null) {
            LOGGER.error("Class not found with classId:{}", approveClassRequest.getClassId());
            throw new ServiceException(ErrorCodeEnum.CLASSROOM_NOT_FOUND);
        }

        ClassEnroll enrollRequest = teacherDao.getEnrollRequestByClassIdAndStudentId(approveClassRequest.getClassId(), approveClassRequest.getStudentId());
        if (enrollRequest == null) {
            LOGGER.error("Enroll request not found");
            throw new ServiceException(ErrorCodeEnum.APPROVE_REQUEST_NOT_FOUND);
        }
        if (!Objects.equals(enrollRequest.getTeacherId(), WebUtils.getUserId())) {
            LOGGER.error("user with Id:{} doesn't have permission to approve class with Id:{}", WebUtils.getUserId(), classDetails.getClassId());
            throw new ServiceException(ErrorCodeEnum.APPROVE_CLASS_FORBIDDEN);
        }
        enrollRequest.setEnrollStatus(1);
        teacherDao.save(enrollRequest);
        //send notification
        notificationUtils.triggerNotification(approveClassRequest.getStudentId(),
                Constants.APPROVE_MESSAGE + classDetails.getClassName() ,
                Constants.APPROVE_TITLE);
        return responseBuilder.buildApproveClassResponse("class approved", approveClassRequest.getClassId(), approveClassRequest.getStudentId());
    }

    @Override
    public GetTimeTableResponse getTimeTable(Long classRoomId) {
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
    public GetClassRoomResponse getClassRoom(Long classId) {
        return null;
    }

    @Override
    @Transactional
    public GetTeacherClassesResponse getOwnClasses(Long userId, Long isScheduled) {
        List<GetTeacherClassesResponseModel> classRooms = teacherDao.getClass(userId, isScheduled);
        return teacherResponseBuilder.buildGetOwnClassesResponse(classRooms);
    }

    @Override
    @Transactional
    public UnEnrollResponse unEnrollClass(EnrollClassRequest enrollClassRequest) {
        ClassRoom classDetails = teacherDao.getClassRoomByClassId(enrollClassRequest.getClassId());
        if (classDetails == null) {
            LOGGER.error("Class not found with classId:{}", enrollClassRequest.getClassId());
            throw new ServiceException(ErrorCodeEnum.CLASSROOM_NOT_FOUND);
        }

        ClassEnroll enrollmentExists = studentDao.getEnrolledClassByClassIdAndStudentId(enrollClassRequest.getClassId(), enrollClassRequest.getStudentId());
        if (enrollmentExists == null) {
            LOGGER.error("Student not enrolled to the specific class with classId:{}", enrollClassRequest.getClassId());
            throw new ServiceException(ErrorCodeEnum.STUDENT_UN_ENROLL_CLASS_NOT_ENROLLED);
        }

        ArchiveClassEnroll archiveClassEnroll = new ArchiveClassEnroll();
        BeanUtils.copyProperties(enrollmentExists, archiveClassEnroll);
        teacherDao.saveArchiveClassEnroll(archiveClassEnroll);
        teacherDao.deleteClassEnrollFromTeacher(enrollmentExists);

        LOGGER.debug("Class UnEnrollment was successful with enrollmentId:{}", enrollmentExists.getEnrollId());
        return responseBuilder.buildUnEnrollClassResponse("Classroom un enrolled successfully", 2001);
    }

    @Override
    public GetEnrollListResponse getApprovalList(GetEnrollListRequest enrollListRequest) {

        Page<EnrollDetails> enrollRequestList = teacherDao.getEnrollRequestList(enrollListRequest);
        if (!enrollRequestList.hasContent()) {
            throw new ServiceException(ErrorCodeEnum.DATA_NOT_FOUND);
        }
        PagedData<EnrollDetails> pagedEnrollList = setPageResponse(enrollRequestList);

        return teacherResponseBuilder.buildGetEnrollRequestListResponse(pagedEnrollList);
    }


    @Override
    public GetTeacherClassesResponse getOwnClassesOnSpecificDay(Long userId, String day) {
        List<GetTeacherClassesResponseModel> classRooms = teacherDao.getTeacherOwnClassOnSpecificDay(userId, day);
        return teacherResponseBuilder.buildGetOwnClassesResponse(classRooms);
    }

    @Override
    public GetTeacherClassDetailsResponse getSpecificClassDetails(Long classId) {
        return teacherResponseBuilder.buildGetTeacherClassDetailsResponse(teacherDao.getSpecificClassDetails(classId));
    }

    @Override
    public GetTeacherClassDetailsResponse getAllClassDetails(Long userId, Long isScheduled) {
        return teacherResponseBuilder.buildGetTeacherClassDetailsResponse(teacherDao.getAllClassDetails(userId, isScheduled));
    }

    private List<ScheduledClass> setTimeTable(List<TimeTableSession> timeTableSessions, Long classId, Long teacherId,
                                              Integer repeat) {
        teacherDao.deleteScheduledClasses(classId);
        // ScheduledClass existingTimetable = teacherDao.getTimeTableForClass(classId);
       /* if (existingTimetable != null) {
            teacherDao.deleteScheduledClassByClassId(existingTimetable);
            LOGGER.debug("Old timetable entries deleted for current classId:{}", classId);
        }*/

        List<ScheduledClass> newSchedulesList = new ArrayList<>();
        if (!timeTableSessions.isEmpty()) {
            String prevDay = null;
            Integer slNo = 0;
            for (TimeTableSession session : timeTableSessions) {
                ScheduledClass scheduledClass = new ScheduledClass();
                scheduledClass.setClassRoomId(classId);
                scheduledClass.setTeacherId(teacherId);
                scheduledClass.setDay(session.getDay());
                scheduledClass.setRepeatEnabled(repeat);
                if (session.getDay().equals(prevDay)) {
                    scheduledClass.setSlNo(++slNo);
                } else {
                    slNo = 0;
                    scheduledClass.setSlNo(slNo);
                }
                scheduledClass.setStartHour(session.getStart().getHour());
                scheduledClass.setStartMin(session.getStart().getMin());
                scheduledClass.setEndHour(session.getEnd().getHour());
                scheduledClass.setEndMin(session.getEnd().getMin());
                newSchedulesList.add(scheduledClass);
                prevDay = session.getDay();
            }
        }
        LOGGER.debug("new schedules for classId:{} are :{}", classId, newSchedulesList);
        return newSchedulesList;
    }

    private User setUser(TeacherSignUpRequest teacherSignUpRequest) {
        User user = new User();
        user.setName(teacherSignUpRequest.getUserName());
        user.setPhoneNumber(teacherSignUpRequest.getNumber());
        user.setUserStatus(teacherSignUpRequest.getUserStatus());
        user.setUserType(teacherSignUpRequest.getUserType());
        user.setProfilePicUrl(teacherSignUpRequest.getProfile_url());
        return user;
    }

    private List<Subject> setSubjects(Long userId, TeacherSignUpRequest teacherSignUpRequest) {
        List<Subject> subjects = new ArrayList<>();

        Long[] subjectCodes = teacherSignUpRequest.getSubjectCode();
        for (Long subjectCode : subjectCodes) {
            Subject subject = new Subject();
            subject.setCreatedBy(userId);
            subject.setSubjectCode(subjectCode);
            subject.setSubjectName(Constants.subject.get(subjectCode));
            subjects.add(subject);
        }
        return subjects;
    }

    protected <T> PagedData<T> setPageResponse(Page<T> pageInfo) {
        long remainCount = pageInfo.getTotalElements() - ((long) (pageInfo.getNumber() + 1) * pageInfo.getSize());
        if (remainCount < 0) {
            remainCount = 0L;
        }
        PagedData<T> pagedData = new PagedData<>();
        pagedData.setList(pageInfo.getContent());
        PageDetails pageDetails = new PageDetails();
        pageDetails.setPage(pageInfo.getNumber() + 1);
        pageDetails.setPageSize(pageInfo.getSize());
        pageDetails.setPageCount(pageInfo.getTotalPages());
        pageDetails.setTotalElements(pageInfo.getTotalElements());
        pageDetails.setRemainingElements(remainCount);
        pagedData.setPageDetails(pageDetails);
        return pagedData;
    }
}
