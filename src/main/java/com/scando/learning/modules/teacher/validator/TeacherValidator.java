package com.scando.learning.modules.teacher.validator;

import com.scando.learning.common.constants.Constants;
import com.scando.learning.common.constants.ErrorCodeEnum;
import com.scando.learning.common.constants.ScheduleDayType;
import com.scando.learning.common.exception.EmptyInputException;
import com.scando.learning.common.exception.ServiceException;
import com.scando.learning.common.models.rest.Status;
import com.scando.learning.common.utils.WebUtils;
import com.scando.learning.modules.auth.dao.UserDao;
import com.scando.learning.modules.teacher.models.rest.TeacherSignUpRequest;
import com.scando.learning.modules.teacher.models.rest.TimeTableSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class TeacherValidator {

    @Autowired
    UserDao userDao;

    public void getOwnClassesRequestValidator(Long userId, String day, Status status) {
        if (Objects.isNull(userId)) {
            status.setEndTime(System.currentTimeMillis());
            throw new EmptyInputException(status, ErrorCodeEnum.INPUT_FIELDS_EMPTY);
        }
        if (!Arrays.stream(ScheduleDayType.values()).anyMatch((t) -> t.getCode().equals(day))) {
            throw new ServiceException(ErrorCodeEnum.INVALID_DAYS_GIVEN);
        }
    }

    public void validateClassSchedules(List<TimeTableSession> request) {

        if (request == null || request.isEmpty()) {
            throw new EmptyInputException(ErrorCodeEnum.CREATE_CLASSROOM_VALIDATION);
        }

        for (TimeTableSession sessions : request) {

            if (sessions.getStart() == null || sessions.getEnd() == null) {
                throw new EmptyInputException(ErrorCodeEnum.CREATE_CLASSROOM_VALIDATION);
            }
            if (sessions.getStart().getHour() == null || sessions.getStart().getMin() == null ||
                    sessions.getEnd().getHour() == null || sessions.getEnd().getMin() == null) {
                throw new EmptyInputException(ErrorCodeEnum.CREATE_CLASSROOM_VALIDATION);
            }
            if (!sessions.getDay().equals(ScheduleDayType.MON.getCode()) && !sessions.getDay().equals(ScheduleDayType.TUE.getCode())
                    && !sessions.getDay().equals(ScheduleDayType.WED.getCode()) && !sessions.getDay().equals(ScheduleDayType.THU.getCode())
                    && !sessions.getDay().equals(ScheduleDayType.FRI.getCode()) && !sessions.getDay().equals(ScheduleDayType.SAT.getCode())
                    && !sessions.getDay().equals(ScheduleDayType.SUN.getCode())) {
                LOGGER.error("Invalid days given for creating timeTable. Day :{}", sessions.getDay());
                throw new ServiceException(ErrorCodeEnum.INVALID_DAYS_GIVEN);
            }
        }
    }

    public void validateClassTypes(Integer type) {
        if (!type.equals(1) && !type.equals(2) && !type.equals(3) && !type.equals(4)) {
            LOGGER.error("Invalid classType given type:{}", type);
            throw new ServiceException(ErrorCodeEnum.INVALID_CLASS_TYPE);
        }
    }

    public void ValidateCreateTeacherRequest(TeacherSignUpRequest teacherSignUpRequest, Status status) {
        if (Objects.isNull(teacherSignUpRequest.getNumber())) {
            status.setEndTime(System.currentTimeMillis());
            throw new EmptyInputException(status, ErrorCodeEnum.INPUT_FIELDS_EMPTY);
        }
        if (Objects.isNull(teacherSignUpRequest.getUserName())) {
            status.setEndTime(System.currentTimeMillis());
            throw new EmptyInputException(status, ErrorCodeEnum.INPUT_FIELDS_EMPTY);
        }
        if (Objects.isNull(teacherSignUpRequest.getUserType())) {
            status.setEndTime(System.currentTimeMillis());
            throw new EmptyInputException(status, ErrorCodeEnum.INPUT_FIELDS_EMPTY);
        }

        LOGGER.debug("Checking userType is Valid or not");
        if (!teacherSignUpRequest.getUserType().equals(0)) {
            status.setEndTime(System.currentTimeMillis());
            throw new ServiceException(ErrorCodeEnum.INVALID_USER_TYPE);
        }

        if (Objects.isNull(teacherSignUpRequest.getProfile_url())) {
            status.setEndTime(System.currentTimeMillis());
            throw new EmptyInputException(status, ErrorCodeEnum.INPUT_FIELDS_EMPTY);
        }

        LOGGER.debug("Checking SubjectCode is Valid or Not");
        for (Long subjectCode : teacherSignUpRequest.getSubjectCode()) {
            if (!Constants.subject.containsKey(subjectCode)) {
                throw new ServiceException(ErrorCodeEnum.INVALID_SUBJECT_CODE,
                        "SubjectCode : ".concat(subjectCode + " is invalid"));
            }
        }

        LOGGER.debug("Check the PhoneNumber is already used");
        if (userDao.getUserByNumber(teacherSignUpRequest.getNumber()) != null) {
            throw new ServiceException(ErrorCodeEnum.PHONE_NUMBER_ALREADY_EXIST);
        }

        LOGGER.debug("Check weather the phone number is verified");
        if (userDao.getExistingOtp(teacherSignUpRequest.getNumber()) == null) {
            throw new ServiceException(ErrorCodeEnum.PHONE_NUMBER_NOT_VERIFIED);
        }
    }

    public void getAllClassesRequestValidator(Long userId, Long isScheduled, Status status) {
        if (Objects.isNull(userId)) {
            status.setEndTime(System.currentTimeMillis());
            throw new EmptyInputException(status, ErrorCodeEnum.INPUT_FIELDS_EMPTY);
        }
        if (!WebUtils.getUserId().equals(userId)) {
            throw new ServiceException(ErrorCodeEnum.USER_TOKEN_MISMATCH);
        }
        if (!Objects.isNull(isScheduled)) {
            if (!(isScheduled.equals(1L) || isScheduled.equals(0L))) {
                throw new ServiceException(ErrorCodeEnum.INVALID_SCHEDULED_TYPE);
            }
        }
    }

    public void getSpecificClassDetailsRequestValidator(Long classId, Status status) {
        if (Objects.isNull(classId)) {
            status.setEndTime(System.currentTimeMillis());
            throw new EmptyInputException(status, ErrorCodeEnum.INPUT_FIELDS_EMPTY);
        }
    }

    public void getAllClassDetailsRequestValidator(Long userId, Long isScheduled, Status status) {
        if (Objects.isNull(userId)) {
            status.setEndTime(System.currentTimeMillis());
            throw new EmptyInputException(status, ErrorCodeEnum.INPUT_FIELDS_EMPTY);
        }
        if (!WebUtils.getUserId().equals(userId)) {
            throw new ServiceException(ErrorCodeEnum.USER_TOKEN_MISMATCH);
        }
        if (!Objects.isNull(isScheduled)) {
            if (!(isScheduled.equals(1L) || isScheduled.equals(0L))) {
                throw new ServiceException(ErrorCodeEnum.INVALID_SCHEDULED_TYPE);
            }
        }
    }

    public void validateEnrollListRequestStatus(Integer type) {
        if (!type.equals(1) && !type.equals(0)) {
            LOGGER.error("Invalid enrollStatus. given type:{}", type);
            throw new ServiceException(ErrorCodeEnum.GET_ENROLL_REQUEST_LIST_ENROLL_STATUS_VALIDATION);
        }
    }

}
