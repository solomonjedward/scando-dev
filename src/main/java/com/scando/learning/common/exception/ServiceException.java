package com.scando.learning.common.exception;

import com.scando.learning.common.constants.ErrorCodeEnum;
import com.scando.learning.common.models.rest.Status;
import lombok.Getter;

import java.util.Map;

@Getter
public class ServiceException extends ScandoRuntimeException{

    private static final long serialVersionUID = 1L;

    private final String message;

    private final ErrorCodeEnum errorCode;

    private final Status status;


    public ServiceException(ErrorCodeEnum errorCode, String message) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
        this.status = null;
    }

    public ServiceException(ErrorCodeEnum errorCode, Throwable cause) {
        super(errorCode.getDescription(), cause);
        this.errorCode = errorCode;
        this.message = errorCode.getDescription();
        this.status = null;
    }

    public ServiceException(ErrorCodeEnum errorCode, String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.errorCode = errorCode;
        this.status = null;
    }

    public ServiceException(Status status, ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getDescription());
        this.message = errorCodeEnum.getDescription();
        this.status = status;
        this.errorCode = errorCodeEnum;
    }

    public ServiceException(ErrorCodeEnum errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
        this.message = errorCode.getDescription();
        this.status = null;
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
