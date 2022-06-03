package com.scando.learning.modules.auth.controller;

import com.scando.learning.LearningApplication;
import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.common.dao.ApplicationDao;
import com.scando.learning.common.models.Otp;
import com.scando.learning.common.models.User;
import com.scando.learning.common.models.rest.Status;
import com.scando.learning.common.service.AwsSnsService;
import com.scando.learning.modules.auth.dao.UserDao;
import com.scando.learning.modules.auth.model.rest.*;
import com.scando.learning.modules.auth.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = LearningApplication.class)
public class OtpGenerateControllerTest extends AbstractAuthControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private AwsSnsService snsService;

    @MockBean
    private UserDao userService;

    @MockBean
    private ApplicationDao appService;

    @Test
    void generateOtpTestWithValidNumber() throws Exception {

        OtpGenerateRequest otpRequest = getOtpGenerateRequest("+911234567890", 1L);
        OtpGenerateResponse otpResponse = getOtpGenerateResponse(getStatus("Success", 20001), getOtpModel("Otp generated successfully", getOtpData().getOtpGenerated()));

        Mockito.when(authService.generateOtp(otpRequest)).thenReturn(otpResponse);

//        doNothing().when(snsService).sendMessage(any(), any());
//        doNothing().when(userService).saveGeneratedOtp(any());
        when(userService.getExistingOtp(any())).thenReturn(getOtpData());
        when(userService.getUserByNumber(any())).thenReturn(getUser());
        when(appService.getAppInfo(any())).thenReturn(getAppInfo(otpRequest.getAppId()));

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.GENERATE_OTP)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson(otpRequest))).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void generateOtpTestWithInValidNumber() throws Exception {

        OtpGenerateRequest otpRequest = getOtpGenerateRequest("+9112390", 1L);
        OtpGenerateResponse otpResponse = getOtpGenerateResponse(getStatus("Success", 20001), getOtpModel("Otp generated successfully", getOtpData().getOtpGenerated()));

        Mockito.when(authService.generateOtp(otpRequest)).thenReturn(otpResponse);

        when(userService.getExistingOtp(any())).thenReturn(null);
        when(userService.getUserByNumber(any())).thenReturn(getUser());
        when(appService.getAppInfo(any())).thenReturn(getAppInfo(otpRequest.getAppId()));
        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.GENERATE_OTP)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson(otpRequest))).andExpect(MockMvcResultMatchers.status().isOk());
    }

    protected OtpGenerateRequest getOtpGenerateRequest(String number, Long appId){
        return OtpGenerateRequest.builder()
                .number(number).appId(appId).build();
    }

    protected OtpGenerateResponse getOtpGenerateResponse(Status status, OtpModel otp){
        return OtpGenerateResponse.builder()
                .status(status)
                .data(otp)
                .build();
    }

    protected OtpModel getOtpModel(String message, int otpCode){
        return OtpModel.builder()
                .message(message)
                .otpCode(otpCode)
                .build();
    }

    protected Otp getOtpData(){
        Otp otpData = new Otp();
        otpData.setOtpGenerated(1234);
        otpData.setPhoneNumber("911234567890");
        return otpData;
    }

    protected User getUser(){
        return null;
    }
}
