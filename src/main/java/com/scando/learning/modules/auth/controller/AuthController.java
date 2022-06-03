package com.scando.learning.modules.auth.controller;

import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.common.constants.ErrorCodeEnum;
import com.scando.learning.common.exception.ControllerException;
import com.scando.learning.common.exception.ServiceException;
import com.scando.learning.common.models.SwaggerHeads;
import com.scando.learning.common.ErrorResponseBuilder.ResponseBuilder;
import com.scando.learning.common.models.rest.Status;
import com.scando.learning.common.utils.WebUtils;
import com.scando.learning.modules.auth.model.rest.*;
import com.scando.learning.modules.auth.service.AuthService;
import com.scando.learning.modules.auth.validator.AuthValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.Arrays;


@RestController
@Api(tags = {SwaggerHeads.AUTH_API})
@Slf4j
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    AuthValidator authValidator;

    @PostMapping(ApiUrls.USER_CHECK_ACCOUNT)
    @ApiOperation(value = "To check the account exits or not")
    public ResponseEntity<CheckResponse> userCheckAccount(@Valid @RequestBody CheckRequest checkRequest,
                                                          BindingResult bindingResult, ServletRequest request) {
        Status status = WebUtils.getStatus();
        status.setApiId(2);
        WebUtils.setStatus(status);
        try {
            if (bindingResult.hasErrors()) {
                throw new ControllerException(
                        bindingResult,
                        ErrorCodeEnum.CHECK_REQUEST_INPUT_VALIDATION_FAILED
                );
            }
            authValidator.validateCheckRequest(checkRequest);

            final CheckResponse checkResponse = authService.checkAccount(checkRequest);
            return ResponseEntity.ok(checkResponse);
        } catch (Exception e) {
            LOGGER.error(Arrays.toString(e.getStackTrace()));
            throw e;
        }
    }

    @PostMapping(ApiUrls.GENERATE_OTP)
    @ApiOperation(value = "To generate Otp")
    public ResponseEntity<OtpGenerateResponse> generateOtp(@RequestBody @Valid OtpGenerateRequest otpGenerateRequest, BindingResult bindingResult) {

        try {
            Status status = WebUtils.getStatus();
            status.setApiId(3);
            WebUtils.setStatus(status);
            if (bindingResult.hasErrors()) {
                LOGGER.debug("INPUT VALIDATION FAILED");
                throw new ControllerException(
                        bindingResult,
                        ErrorCodeEnum.GENERATE_OTP_INPUT_VALIDATION_FAILED
                );
            }

            final OtpGenerateResponse otpGenerateResponse = authService.generateOtp(otpGenerateRequest);
            return ResponseEntity.ok(otpGenerateResponse);

        } catch (Exception e) {
            LOGGER.error(Arrays.toString(e.getStackTrace()));
            throw e;
        }
    }

    @PostMapping(ApiUrls.VERIFY_OTP)
    @ApiOperation(value = "Verify Otp")
    public ResponseEntity<VerifyOtpResponse> verifyOtp(@RequestBody @Valid VerifyOtpRequest verifyOtpRequest, BindingResult bindingResult) {
        Status status = WebUtils.getStatus();
        status.setApiId(5);
        WebUtils.setStatus(status);
        if (bindingResult.hasErrors()) {
            LOGGER.debug("INPUT VALIDATION FAILED");
            throw new ControllerException(
                    bindingResult,
                    ErrorCodeEnum.VERIY_OTP_INPUT_VALIDATION_FAILED
            );
        }

        try {
            VerifyOtpResponse apiResponse = authService.verifyOtp(verifyOtpRequest);
            return ResponseEntity.ok(apiResponse);
        } catch (ServiceException ex) {
            throw new ServiceException(ex.getErrorCode());
        }
    }

    @PostMapping(ApiUrls.PROFILE_UPLOAD)
    @ApiOperation(value = "Upload profile")
    public ResponseEntity<ProfileUploadResponse> uploadProfile(@RequestParam(value = "file") MultipartFile multipartFile) {
        try {
            Status status = new Status();
            status.setApiId(3);
            status.setStartTime(System.currentTimeMillis());
            ProfileUploadRequest profileUploadRequest = new ProfileUploadRequest();
            profileUploadRequest.setMultipartFile(multipartFile);

            authValidator.validateProfileUploadRequest(profileUploadRequest, status);
            final ProfileUploadResponse profileUploadResponse = authService.uploadProfile(profileUploadRequest);
            return ResponseEntity.ok(profileUploadResponse);
        } catch (Exception exception) {
            LOGGER.error("Profile Upload failed");
            throw exception;
        }
    }

    @DeleteMapping(ApiUrls.LOGOUT)
    @ApiOperation("Api to logout from the application")
    public ResponseEntity<LogoutResponse> logout(ServletRequest request) {
        final String token = WebUtils.getHeader("Authorization").split(" ")[1];
        final LogoutResponse logoutResponse = authService.logout(token);
        return ResponseEntity.ok(logoutResponse);
    }

}
