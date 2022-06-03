package com.scando.learning.common.exception;

import com.scando.learning.common.constants.ErrorCodeEnum;
import com.scando.learning.common.models.rest.Status;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class EmptyInputException extends RuntimeException{
    private int code;
    private HttpStatus status;
    private String description;
    private Status status1;
    private ErrorCodeEnum errorCodeEnum;

    public EmptyInputException(int code, HttpStatus status, String description) {
        this.code = code;
        this.status = status;
        this.description = description;
    }

    public EmptyInputException(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public EmptyInputException(Status status1, ErrorCodeEnum errorCodeEnum) {
        this.status1 = status1;
        this.errorCodeEnum = errorCodeEnum;
    }

    public EmptyInputException(ErrorCodeEnum errorCodeEnum) {
        this(errorCodeEnum.getCode(), errorCodeEnum.getStatus(), errorCodeEnum.getDescription());
    }
}
