package com.scando.learning.modules.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scando.learning.common.constants.VerifyType;
import com.scando.learning.common.exception.ScandoException;
import com.scando.learning.common.models.Application;
import com.scando.learning.common.models.rest.Status;
import com.scando.learning.modules.auth.model.rest.*;

public class AbstractAuthControllerTest {

    protected CheckRequest getCheckRequest(String number, Long appId) {
        return CheckRequest.builder()
                .mobileNumber(number)
                .appId(appId)
                .build();
    }

    protected CheckResponse getCheckResponse(Status status , Check check) {
        return CheckResponse.builder()
                .status(status)
                .data(check)
                .build();
    }

    protected Status getStatus(String status, Integer code ) {
        return Status.builder()
                .statusCode(code)
                .build();
    }

    protected Check getCheck(Integer code , String status) {
        return  Check.builder()
                .status(status)
                .code(code).build();
    }

    protected String getJson(Object object) throws ScandoException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException var3) {
            throw new ScandoException("Exception while converting object to json", var3);
        }
    }

    protected Application getAppInfo(Long appId) {
        Application appInfo = new Application();
        appInfo.setAppId(appId);
        appInfo.setOsInfo("s");
        appInfo.setDeviceId("a");
        return appInfo;
    }

    protected VerifyOtpRequest getVerifyOtpRequest(String mobileNumber, Integer otp , Long appId){
        return VerifyOtpRequest.builder()
                .otp(otp)
                .appId(appId)
                .number(mobileNumber)
                .build();
    }

    protected VerifyOtpResponse getVerifyOtp(String message , Status status) {
        return VerifyOtpResponse.builder()
                .status(status)
                .data(VerifyOtp.builder()
                        .code(VerifyType.USER_LOGIN.getCode())
                .message(message)
                .build()).build();
    }

    protected VerifyOtpResponse getLoginResponse(Login login , Status status) {
        return VerifyOtpResponse.builder()
                .status(status)
                .data(login).build();
    }

    protected Login getLogin(String token , String refreshToken , Long userId, Long appId) {
        return Login.builder()
                .token(token)
                .refreshToken(refreshToken)
                .appId(appId)
                .userId(userId)
                .build();
    }

}
