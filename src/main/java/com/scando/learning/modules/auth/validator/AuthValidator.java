package com.scando.learning.modules.auth.validator;

import com.scando.learning.common.constants.ErrorCodeEnum;
import com.scando.learning.common.exception.EmptyInputException;
import com.scando.learning.common.exception.MaxUploadSizeExceedException;
import com.scando.learning.common.exception.ServiceException;
import com.scando.learning.common.models.rest.Status;
import com.scando.learning.modules.auth.dao.UserDao;
import com.scando.learning.modules.auth.model.rest.CheckRequest;
import com.scando.learning.modules.auth.model.rest.OtpGenerateRequest;
import com.scando.learning.modules.auth.model.rest.ProfileUploadRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthValidator {

    @Autowired
    UserDao userDao;

    public void validateProfileUploadRequest(ProfileUploadRequest profileUploadRequest, Status status) {
        System.out.println("here" + profileUploadRequest.getMultipartFile().getSize());
        if (profileUploadRequest.getMultipartFile().getSize() > 15000000) {
            status.setEndTime(System.currentTimeMillis());
            throw new MaxUploadSizeExceedException(status, ErrorCodeEnum.PROFILE_UPLOAD_MAX_PROFILE_UPLOAD_SIZE);
        }
        if (profileUploadRequest.getMultipartFile() == null) {
            throw new ServiceException(ErrorCodeEnum.FILE_NOT_FOUND);
        }

        if (!FilenameUtils.getExtension(profileUploadRequest.getMultipartFile().getOriginalFilename()).isEmpty()) {
            //TODO need to check the type of file supported
        }
    }

    public void validateCheckRequest(CheckRequest checkRequest) {

    }

    public void validateGenerateOtpRequest(OtpGenerateRequest otpGenerateRequest) {
        if (Objects.isNull(otpGenerateRequest.getNumber())) {
            throw new EmptyInputException(ErrorCodeEnum.INPUT_FIELDS_EMPTY.getCode(),
                    ErrorCodeEnum.INPUT_FIELDS_EMPTY.getStatus(), ErrorCodeEnum.INPUT_FIELDS_EMPTY.getDescription());
        }
    }
}
