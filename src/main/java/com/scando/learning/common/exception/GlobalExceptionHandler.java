package com.scando.learning.common.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.scando.learning.common.ErrorResponseBuilder.Data;
import com.scando.learning.common.ErrorResponseBuilder.ErrorResponse;
import com.scando.learning.common.ErrorResponseBuilder.ResponseBuilder;
import com.scando.learning.common.constants.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ResponseBuilder responseBuilder;


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception exception,
                                                                   HttpServletRequest request,
                                                                   HttpServletResponse response) {

        Data data = null;
        if(exception instanceof InvalidFormatException) {
             data = Data.builder()
                    .code(ErrorCodeEnum.INVALID_FORMAT.getCode())
                    .message(ErrorCodeEnum.INVALID_FORMAT.getDescription())
                    .errorDetails(exception.getMessage())
                    .build();
        } else {
            data = Data.builder()
                    .code(ErrorCodeEnum.SERVICE_UNAVAILABLE.getCode())
                    .message(ErrorCodeEnum.SERVICE_UNAVAILABLE.getDescription())
                    .errorDetails(exception.getMessage())
                    .build();
        }
        return new ResponseEntity<>(responseBuilder.getIndividualResponse(data), HttpStatus.OK);
    }


    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<ErrorResponse> handleEmptyInputException(EmptyInputException emptyInputException) {

        Data data = Data.builder()
                .code(emptyInputException.getCode())
                .message(emptyInputException.getDescription())
                .build();
        return new ResponseEntity<>(responseBuilder.getIndividualResponse(data),
                HttpStatus.OK);
    }


    @ExceptionHandler(MaxUploadSizeExceedException.class)
    public ResponseEntity<ErrorResponse> handleMaxUploadSizeExceedException(MaxUploadSizeExceedException
                                                                                    maxUploadSizeExceedException) {
        Data data = Data.builder()
                .code(maxUploadSizeExceedException.getErrorCodeEnum().getCode())
                .message(maxUploadSizeExceedException.getDescription())
                .build();
        return new ResponseEntity<>(responseBuilder.getIndividualResponse(data),
                HttpStatus.OK);
    }


    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            ControllerException controllerException) {
        List<HashMap<String, String>> error = new ArrayList<>();
       if(null != controllerException.getBindingErrors()) {
           for (FieldError fieldError : controllerException.getBindingErrors().getFieldErrors()) {
               HashMap<String, String> fieldErrors = new HashMap<>();
               fieldErrors.put("rejectedValue",  String.valueOf(fieldError.getRejectedValue()));
               fieldErrors.put("fieldName", fieldError.getField());
               fieldErrors.put("message", fieldError.getDefaultMessage());
               error.add(fieldErrors);
           }
       } else {
           error = null;
       }
        Data data = Data.builder()
                .code(controllerException.getErrorCodeEnum().getCode())
                .message(controllerException.getErrorCodeEnum().getDescription())
                .errorDetails(error)
                .build();
        return new ResponseEntity<>(responseBuilder.getIndividualResponse(data),
                HttpStatus.OK);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(
            ServiceException serviceException) {
        Data data = Data.builder()
                .code(serviceException.getErrorCode().getCode())
                .message(serviceException.getErrorCode().getDescription())
                .message(serviceException.getMessage())
                .build();

        return new ResponseEntity<>(responseBuilder.getIndividualResponse(data), HttpStatus.OK);
    }

}
