package com.scando.learning.common.exception;

import com.scando.learning.common.constants.ErrorCodeEnum;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

@Data
public class ValidationException extends RuntimeException {

    private int code;
    private HttpStatus status;
    private String description;

    public ValidationException(int code, HttpStatus status, String description) {
        this.code = code;
        this.status = status;
        this.description = description;
    }

    public ValidationException(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public ValidationException(ErrorCodeEnum errorCodeEnum) {
        this(errorCodeEnum.getCode(), errorCodeEnum.getStatus(), errorCodeEnum.getDescription());
    }

}
