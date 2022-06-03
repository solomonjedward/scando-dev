package com.scando.learning.modules.student.validator;

import com.scando.learning.common.constants.ErrorCodeEnum;
import com.scando.learning.common.exception.EmptyInputException;
import com.scando.learning.common.exception.ServiceException;
import com.scando.learning.common.models.rest.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class StudentValidator {

    public void validateGetStudentOwnClassRequest(Long userId, String day, Status status) {
        if (Objects.isNull(userId)) {
            status.setEndTime(System.currentTimeMillis());
            throw new EmptyInputException(status, ErrorCodeEnum.INPUT_FIELDS_EMPTY);
        }
        if (Objects.isNull(day)) {
            status.setEndTime(System.currentTimeMillis());
            throw new EmptyInputException(status, ErrorCodeEnum.INPUT_FIELDS_EMPTY);
        }
    }

    public void validateUnEnrollClassRequest(Integer enrollType) {
        if (!enrollType.equals(2)) {
            LOGGER.error("invalid enrollType given, only 2 is accepted to unEnroll class");
            throw new ServiceException(ErrorCodeEnum.STUDENT_ENROLL_CLASS_ENROLL_TYPE_ERROR);
        }
    }

    public void validateGetClassDetailsOfSpecificClassRequest(Long classId, Status status) {
        if (Objects.isNull(classId)) {
            status.setEndTime(System.currentTimeMillis());
            throw new EmptyInputException(status, ErrorCodeEnum.INPUT_FIELDS_EMPTY);
        }
    }

    public void validateGetAllClassDetailsRequest(Long userId) {

    }

    public void validateEnrollListRequestStatus(Integer type) {
        if (!type.equals(1) && !type.equals(0)) {
            LOGGER.error("Invalid enrollStatus. given type:{}", type);
            throw new ServiceException(ErrorCodeEnum.GET_STUDENT_ENROLL_LIST_ENROLL_STATUS_VALIDATION);
        }
    }
}
