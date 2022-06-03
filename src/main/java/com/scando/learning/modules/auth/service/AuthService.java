package com.scando.learning.modules.auth.service;


import com.scando.learning.modules.auth.model.rest.*;

public interface AuthService {


    CheckResponse checkAccount(CheckRequest checkRequest);

    OtpGenerateResponse generateOtp(OtpGenerateRequest otpGenerateRequest);

    VerifyOtpResponse verifyOtp(VerifyOtpRequest verifyOtpRequest);


    ProfileUploadResponse uploadProfile(ProfileUploadRequest profileUploadRequest);

    LogoutResponse logout(String token);




}
