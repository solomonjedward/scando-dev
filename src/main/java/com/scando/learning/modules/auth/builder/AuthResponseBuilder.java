package com.scando.learning.modules.auth.builder;

import com.scando.learning.common.constants.Constants;
import com.scando.learning.common.constants.StatusEnum;
import com.scando.learning.common.models.rest.Status;
import com.scando.learning.common.utils.WebUtils;
import com.scando.learning.modules.auth.model.rest.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthResponseBuilder {
    public ProfileUploadResponse buildProfileUploadResponse(String profileUrl, Long requestId) {
        Status status = WebUtils.getStatus();
        status.setEndTime(System.currentTimeMillis());
        status.setStatusCode(StatusEnum.SUCCESS.getCode());
        return ProfileUploadResponse.builder()
                .status(status)
                .data(ProfileUpload.builder().profileUrl(profileUrl)
                        .requestUrlId(requestId)
                        .message(Constants.PROFILE_UPLOAD_SUCCESS).build()).build();
    }

    public CheckResponse buildCheckResponse(Integer code, String status) {
        Status status1 = WebUtils.getStatus();
        status1.setEndTime(System.currentTimeMillis());
        status1.setStatusCode(StatusEnum.SUCCESS.getCode());
        return CheckResponse.builder()
                .status(status1)
                .data(Check.builder().status(status)
                        .code(code).build())
                .build();
    }

    public OtpGenerateResponse buildOtpGenerateResponse(int otpCode, String message) {
        Status status = WebUtils.getStatus();
        status.setEndTime(System.currentTimeMillis());
        status.setStatusCode(StatusEnum.SUCCESS.getCode());
        return OtpGenerateResponse.builder()
                .status(status)
                .data(OtpModel.builder().message(message).otpCode(otpCode).build())
                .build();
    }


    public <T> VerifyOtpResponse buildVerifyOtpResponse(T data) {
        Status status = WebUtils.getStatus();
        status.setEndTime(System.currentTimeMillis());
        status.setStatusCode(StatusEnum.SUCCESS.getCode());
        return VerifyOtpResponse.builder()
                .status(status)
                .data(data)
                .build();
    }

    public LogoutResponse buildLogoutResponse() {
        Status status = WebUtils.getStatus();
        status.setEndTime(System.currentTimeMillis());
        status.setStatusCode(StatusEnum.SUCCESS.getCode());
        return LogoutResponse.builder()
                .status(status)
                .data(Logout.builder()
                        .message("logged out success")
                        .build())
                .build();
    }

}
