package com.scando.learning.common.validator;

import com.scando.learning.common.constants.ErrorCodeEnum;
import com.scando.learning.common.constants.UserType;
import com.scando.learning.common.exception.ControllerException;
import com.scando.learning.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserTypeValidator {

    public static void userTypeValidator(Integer userType , ErrorCodeEnum errorCodeEnum) {
        if(!UserType.STUDENT.getCode().equals(userType)&& !UserType.STUDENT.getCode().equals(userType)){
            LOGGER.debug("User type {} not supported" , userType);
            throw new ServiceException(errorCodeEnum);
        }
    }
}
