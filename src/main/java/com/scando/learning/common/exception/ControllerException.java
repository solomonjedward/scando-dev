package com.scando.learning.common.exception;

import com.scando.learning.common.constants.ErrorCodeEnum;
import com.scando.learning.common.models.rest.Status;
import lombok.Getter;
import org.springframework.validation.Errors;

@Getter
public class ControllerException extends ScandoRuntimeException{

    private static final long serialVersionUID = 1L;

    private static final String INPUT_VALIDATION_ERROR = "Input Validation errors. Unable to proceed with processing";

    private final String message;

    private final ErrorCodeEnum errorCodeEnum;

    private final Status status;


    private final transient Errors bindingErrors;


    public ControllerException(Errors bindingErrors) {
        this(ErrorCodeEnum.INPUT_VALIDATION_FAILED, bindingErrors, INPUT_VALIDATION_ERROR);
    }

    public ControllerException(Errors bindingErrors, String message) {
        this(ErrorCodeEnum.INPUT_VALIDATION_FAILED, bindingErrors, message);
    }

    public ControllerException(ErrorCodeEnum errorCodeEnum, Errors bindingErrors, String message) {
        super(message);
        this.errorCodeEnum = errorCodeEnum;
        this.bindingErrors = bindingErrors;
        this.message = message;
        this.status = null;
    }

    public ControllerException(ErrorCodeEnum errorCodeEnum, Throwable cause) {
        super(errorCodeEnum.getDescription(), cause);
        this.errorCodeEnum = errorCodeEnum;
        this.message = errorCodeEnum.getDescription();
        this.bindingErrors = null;
        this.status = null;
    }

    public ControllerException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getDescription());
        this.errorCodeEnum = errorCodeEnum;
        this.message = errorCodeEnum.getDescription();
        this.bindingErrors = null;
        this.status = null;
    }

    public ControllerException(Status status, Errors bindingErrors, ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getDescription());
        this.message = errorCodeEnum.getDescription();
        this.status = status;
        this.bindingErrors = bindingErrors;
        this.errorCodeEnum = errorCodeEnum;
    }

    public ControllerException(Errors bindingErrors ,ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getDescription());
        this.message = errorCodeEnum.getDescription();
        this.status = null;
        this.bindingErrors = bindingErrors;
        this.errorCodeEnum = errorCodeEnum;
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
