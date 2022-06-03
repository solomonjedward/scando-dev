package com.scando.learning.modules.auth.service.impl;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.scando.learning.common.ErrorResponseBuilder.ResponseBuilder;
import com.scando.learning.common.constants.Constants;
import com.scando.learning.common.constants.ErrorCodeEnum;
import com.scando.learning.common.constants.UserType;
import com.scando.learning.common.constants.VerifyType;
import com.scando.learning.common.dao.ApplicationDao;
import com.scando.learning.common.dao.ArchiveOtpDao;
import com.scando.learning.common.dao.ArchiveUserLoginInfoDao;
import com.scando.learning.common.dao.UserLoginInfoDao;
import com.scando.learning.common.exception.ServiceException;
import com.scando.learning.common.models.*;
import com.scando.learning.common.models.rest.S3RequestDetails;
import com.scando.learning.common.service.AwsSnsService;
import com.scando.learning.common.utils.ScandoUtils;
import com.scando.learning.modules.auth.builder.AuthResponseBuilder;
import com.scando.learning.modules.auth.dao.S3Dao;
import com.scando.learning.modules.auth.dao.UserDao;
import com.scando.learning.modules.auth.model.rest.*;
import com.scando.learning.modules.auth.service.AuthService;
import com.scando.learning.modules.auth.utils.AuthUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserDao userDao;

    @Autowired
    S3Dao s3Dao;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private AuthResponseBuilder responseBuilder;

    @Autowired
    private ResponseBuilder errorResponseBuilder;

    @Autowired
    private AwsSnsService awsSnsService;

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    private UserLoginInfoDao userLoginInfoDao;

    @Autowired
    private ArchiveUserLoginInfoDao archiveUserLoginInfoDao;

    @Autowired
    private ArchiveOtpDao archiveOtpDao;

    @Value("${scando.aws.sns.enabled}")
    private boolean snsEnabled;

    @Override
    public CheckResponse checkAccount(CheckRequest checkRequest) {
        if (ScandoUtils.checkAppId(checkRequest.getAppId())) {
            throw new ServiceException(ErrorCodeEnum.CHECK_REQUEST_INVALID_APP_ID);
        }
        User user = userDao.getUserByNumber(checkRequest.getMobileNumber());
        if (user != null) {
            return responseBuilder.buildCheckResponse(1, "exists");
        } else {
            return responseBuilder.buildCheckResponse(0, "not exists");
        }

    }

    @Override
    @Transactional
    public OtpGenerateResponse generateOtp(OtpGenerateRequest otpGenerateRequest) {
        boolean otpSuccess = false;
        String phoneNumber = otpGenerateRequest.getNumber();
        Application appInfo = applicationDao.getAppInfo(otpGenerateRequest.getAppId());
        if (appInfo == null) {
            LOGGER.error("AppId:{} not registered", otpGenerateRequest.getAppId());
            throw new ServiceException(ErrorCodeEnum.APP_NOT_REGISTERED);
        }

        Otp otpModel = userDao.getExistingOtp(phoneNumber);
        User checkUserExists = userDao.getUserByNumber(phoneNumber);
        int otpGenerated = new Random().nextInt(9000) + 1000;
        String otpMessage = otpGenerated + " is your otp to register with scando";

        if (otpModel == null) {
            otpModel = new Otp();
            otpModel.setRegeneratedStatus(0);
        } else {
            otpModel.setRegeneratedStatus(1);
        }
        if (checkUserExists == null) {
            otpModel.setStatus(3);
        } else {
            otpModel.setStatus(2);
        }
        otpModel.setPhoneNumber(phoneNumber);
        otpModel.setOtpGenerated(otpGenerated);
        otpModel.setExpiryTime(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));

        otpSuccess = !snsEnabled || awsSnsService.sendMessage(otpMessage, phoneNumber);
        if (otpSuccess) {
            userDao.saveGeneratedOtp(otpModel);
            LOGGER.debug("Otp details saved successfully with id:{} ", otpModel.getOtpId());
            return responseBuilder.buildOtpGenerateResponse(otpGenerated, "Otp generated successfully");
        } else {
            LOGGER.debug("Otp generation failed from awsSnsService");
            throw new ServiceException(ErrorCodeEnum.GENERATE_OTP_FAILED);
        }
    }

    @Override
    public VerifyOtpResponse verifyOtp(VerifyOtpRequest verifyOtpRequest) {

        Otp otp = userDao.getByMobileNumberAndGeneratedOtp(verifyOtpRequest.getNumber(), verifyOtpRequest.getOtp());
        if (ScandoUtils.checkAppId(verifyOtpRequest.getAppId())) {
            throw new ServiceException(ErrorCodeEnum.VERIY_OTP_INVALID_APP_ID);
        }
        if (null == otp) {
            LOGGER.debug("invalid otp or mobile number");
            throw new ServiceException(ErrorCodeEnum.VERIY_OTP_INVALID_OTP);
        } else if (otp.getExpiryTime() < System.currentTimeMillis()) {
            throw new ServiceException(ErrorCodeEnum.VERIY_OTP_EXPIRED);
        } else {
            if (otp.getStatus() == 2) {
                User user = userDao.getUserByNumber(otp.getPhoneNumber());
                HashMap<String, String> token = authUtils.getJwtToken(otp.getPhoneNumber(),
                        UserType.getUserType(user.getUserType()), user);
                UserLoginInfo userLoginInfo = userLoginInfoDao.getUserLoginInfoByAppIdandUserId(
                        verifyOtpRequest.getAppId(),
                        user.getUserId());
                if (null == userLoginInfo) {
                    userLoginInfo = UserLoginInfo.builder()
                            .userId(user.getUserId())
                            .appId(verifyOtpRequest.getAppId())
                            .build();
                }
                userLoginInfo.setToken(token.get("token"));
                userLoginInfo.setRefreshToken(token.get("refreshToken"));
                userLoginInfo.setExpiryTime(Long.valueOf(token.get("expiry")));
                userLoginInfoDao.save(userLoginInfo);
                Login login = Login.builder()
                        .code(VerifyType.USER_LOGIN.getCode())
                        .userId(user.getUserId())
                        .userType(user.getUserType())
                        .userName(user.getName())
                        .appId(verifyOtpRequest.getAppId())
                        .token(token.get("token"))
                        .refreshToken(token.get("refreshToken"))
                        .profileUrl(user.getProfilePicUrl())
                        .build();
                ArchiveOtp archiveOtp = new ArchiveOtp();
                BeanUtils.copyProperties(otp, archiveOtp);
                if (null != archiveOtpDao.save(archiveOtp)) {
                    userDao.deleteGeneratedOtp(otp);
                }
                return responseBuilder.buildVerifyOtpResponse(login);
            } else if (otp.getStatus() == 1) {
                LOGGER.debug("Otp used for number {}", otp.getPhoneNumber());
                throw new ServiceException(ErrorCodeEnum.VERIY_OTP_USED);
            }
            otp.setStatus(1);
            otp.setExpiryTime(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10));
            userDao.saveGeneratedOtp(otp);
            return responseBuilder.buildVerifyOtpResponse(VerifyOtp.builder()
                    .code(VerifyType.OTP_VERIFICATION.getCode())
                    .message("Otp verified").build());
        }
    }

    @Override
    public ProfileUploadResponse uploadProfile(ProfileUploadRequest profileUploadRequest) {
        final File file = convertMultiPartFileToFile(profileUploadRequest.getMultipartFile());
        final String fileName = "users/" + "/profile/" + profileUploadRequest.getMultipartFile().getOriginalFilename()
                + "-avatar";
        final PutObjectRequest putObjectRequest = new PutObjectRequest(Constants.PROFILE_S3_BUCKET, fileName, file);
        final AccessControlList accessControlList = new AccessControlList();
        accessControlList.grantPermission(GroupGrantee.AllUsers, Permission.Read);
        putObjectRequest.setAccessControlList(accessControlList);
        s3Client.putObject(putObjectRequest);
        final URL s3Url = s3Client.getUrl(Constants.PROFILE_S3_BUCKET, fileName);
        file.delete();

        S3RequestDetails s3RequestDetails = new S3RequestDetails();
        s3RequestDetails.setUrl(s3Url.toString());
        S3RequestDetails s3RequestDetails1 = s3Dao.save(s3RequestDetails);

        ProfileUploadResponse profileUploadResponse = responseBuilder
                .buildProfileUploadResponse(s3Url.toString(), s3RequestDetails1.getRequestId());
        return profileUploadResponse;
    }

    @Override
    public LogoutResponse logout(String token) {
       UserLoginInfo userLoginInfo = userLoginInfoDao.getByToken(token);
       if(null != userLoginInfo) {
           ArchiveUserLoginInfo archiveUserLoginInfo = new ArchiveUserLoginInfo();
           BeanUtils.copyProperties(userLoginInfo , archiveUserLoginInfo);
           if(null != archiveUserLoginInfoDao.save(archiveUserLoginInfo))
           {
               userLoginInfoDao.delete(userLoginInfo);
           }
       }
       return responseBuilder.buildLogoutResponse();
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        final File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (Exception e) {
            //TODO need to handle
        }
        return convertedFile;
    }
}
