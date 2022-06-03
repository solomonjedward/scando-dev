package com.scando.learning.modules.auth.controller;

import com.scando.learning.LearningApplication;
import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.modules.auth.model.rest.*;
import com.scando.learning.modules.auth.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = LearningApplication.class)
class AuthControllerTest extends AbstractAuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    void userCheckAccountValidNumber() throws Exception {

        CheckRequest checkRequest = getCheckRequest("+919498741259", 1L );

        CheckResponse checkResponse = getCheckResponse(getStatus("Success", 20001),getCheck(0,
                "not exists"));

        Mockito.when(authService.checkAccount(checkRequest)).thenReturn(checkResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.USER_CHECK_ACCOUNT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson(checkRequest))).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.status").value("not exists"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void userCheckAccountExistingNumber() throws Exception {

        CheckRequest checkRequest = getCheckRequest("+919498741259", 1L );
        CheckResponse checkResponse = getCheckResponse(getStatus("Success", 20001),getCheck(1,
                "exists"));

        Mockito.when(authService.checkAccount(checkRequest)).thenReturn(checkResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.USER_CHECK_ACCOUNT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJson(checkRequest))).andDo(MockMvcResultHandlers.print())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.status").value("exists"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void userCheckAccountInvalidNumber() throws Exception {

        CheckRequest checkRequest = getCheckRequest("+919498741259", 1L );
        CheckResponse checkResponse = getCheckResponse(getStatus("Success", 20001), getCheck(1,
                "exists"));

        Mockito.when(authService.checkAccount(checkRequest)).thenReturn(checkResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.USER_CHECK_ACCOUNT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson(checkRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void userCheckAccountWrongJson() throws Exception {

        CheckRequest checkRequest = getCheckRequest("+919498741259", 1L );
        CheckResponse checkResponse = getCheckResponse(getStatus("Success", 20001), getCheck(1,
                "exists"));

        Mockito.when(authService.checkAccount(checkRequest)).thenReturn(checkResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.USER_CHECK_ACCOUNT)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"mobileNumber\": \"+919498741259\"\n" +
                        ""))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void userCheckAccountWithoutInput() throws Exception {

        CheckRequest checkRequest = getCheckRequest("+919498741259", 1L );
        CheckResponse checkResponse = getCheckResponse(getStatus("Success", 20001), getCheck(1,
                "exists"));

        Mockito.when(authService.checkAccount(checkRequest)).thenReturn(checkResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.USER_CHECK_ACCOUNT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void userCheckAccountInvalidContent() throws Exception {

        CheckRequest checkRequest = getCheckRequest("+919498741259", 1L );
        CheckResponse checkResponse = getCheckResponse(getStatus("Success", 20001), getCheck(1,
                "exists"));

        Mockito.when(authService.checkAccount(checkRequest)).thenReturn(checkResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.USER_CHECK_ACCOUNT)
                .contentType(MediaType.APPLICATION_XML)
                .content(getJson(checkRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void userCheckAccountExtraParam() throws Exception {

        CheckRequest checkRequest = getCheckRequest("+919498741259", 1L );
        CheckResponse checkResponse = getCheckResponse(getStatus("Success", 20001), getCheck(1,
                "exists"));

        Mockito.when(authService.checkAccount(checkRequest)).thenReturn(checkResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.USER_CHECK_ACCOUNT)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"mobileNumber\": \"+919498741259\",\n" +
                        "  \"test\":111\n" +
                        "}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void userCheckAccountWithMobileNumberNull() throws Exception {

        CheckRequest checkRequest = getCheckRequest(null, 1L);


       WithOutDataOrNull(getJson(checkRequest),ApiUrls.USER_CHECK_ACCOUNT);
    }

    @Test
    void userCheckAccountWithAppIdNull() throws Exception {

        CheckRequest checkRequest = getCheckRequest("+919497174287", null);


        WithOutDataOrNull(getJson(checkRequest),ApiUrls.USER_CHECK_ACCOUNT);
    }

    @Test
    void userCheckAccountWithoutMobileNumber() throws Exception {

        String json = "{\n" +
                "  \"appId\": 1\n" +
                "}";


        WithOutDataOrNull(json,ApiUrls.USER_CHECK_ACCOUNT);
    }

    @Test
    void userCheckAccountWithoutAppId() throws Exception {

        String json = "{\n" +
                "  \"mobileNumber\": \"+919498741259\"\n" +
                "}";


        WithOutDataOrNull(json,ApiUrls.USER_CHECK_ACCOUNT);
    }

 /*   @Test
    void userCheckAccountWithAppIdString() throws Exception {

        String json = "{\n" +
                "  \"appId\": \"aaaaaaa\",\n" +
                "  \"mobileNumber\": \"+919498741259\"\n" +
                "}";


        WithOutDataOrNull(json,ApiUrls.USER_CHECK_ACCOUNT);
    }*/




    @Test
    void testUploadProfileWithInValidData() throws Exception {
        MockMvc mockMvc1 = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());

        ProfileUploadRequest profileUploadRequest = getProfileUploadRequest(10L);
        Mockito.when(authService.uploadProfile(profileUploadRequest)).thenReturn(new ProfileUploadResponse());
        mockMvc.perform(MockMvcRequestBuilders.multipart(ApiUrls.PROFILE_UPLOAD)
                        .file(firstFile))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void testUploadProfileWithoutUserIdData() throws Exception {
        MockMvc mockMvc1 = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());

        ProfileUploadRequest profileUploadRequest = getProfileUploadRequest(null);
        Mockito.when(authService.uploadProfile(profileUploadRequest)).thenReturn(new ProfileUploadResponse());
        mockMvc.perform(MockMvcRequestBuilders.multipart(ApiUrls.PROFILE_UPLOAD)
                        .file(firstFile))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void testUploadProfileWithOutFile() throws Exception {
        MockMvc mockMvc1 = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());

        ProfileUploadRequest profileUploadRequest = getProfileUploadRequest(10L);
        Mockito.when(authService.uploadProfile(profileUploadRequest)).thenReturn(new ProfileUploadResponse());
        mockMvc.perform(MockMvcRequestBuilders.multipart(ApiUrls.PROFILE_UPLOAD))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    private ProfileUploadRequest getProfileUploadRequest(Long userId) {
        ProfileUploadRequest profileUploadRequest = new ProfileUploadRequest();
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        profileUploadRequest.setMultipartFile(file);
        return profileUploadRequest;
    }

    /*Verify Otp and Login test cases */
    @Test
    void verifyOtpWithValidNumberandValidOtp() throws Exception {
        VerifyOtpRequest verifyOtpRequest = getVerifyOtpRequest("+919497474110",1234,1L);
        VerifyOtpResponse verifyOtpResponse = getVerifyOtp("otp verified",
                getStatus("success",20001));
        Mockito.when(authService.verifyOtp(verifyOtpRequest)).thenReturn(verifyOtpResponse);
        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.VERIFY_OTP)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJson(verifyOtpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message").value("otp verified"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void verifyOtpWithValidNumber() throws Exception {
        VerifyOtpRequest verifyOtpRequest = getVerifyOtpRequest("+91949474110",1234,1L);
        VerifyOtpResponse verifyOtpResponse = getVerifyOtp("otp verified",
                getStatus("success",20001));
        Mockito.when(authService.verifyOtp(verifyOtpRequest)).thenReturn(verifyOtpResponse);
        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.VERIFY_OTP)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJson(verifyOtpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Input validation failed"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void verifyOtpWithValidOtp() throws Exception {
        VerifyOtpRequest verifyOtpRequest = getVerifyOtpRequest("+919494774110",1234,1L);
        VerifyOtpResponse verifyOtpResponse = getVerifyOtp("otp verified",
                getStatus("success",20001));
        Mockito.when(authService.verifyOtp(verifyOtpRequest)).thenReturn(verifyOtpResponse);
        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.VERIFY_OTP)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"number\": \"+919494774110\",\n" +
                                "  \"userId\": 1,\n" +
                                "  \"otp\": \"1234\"\n" +
                                "}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.statusCode")
                        .value(20002))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void verifyOtpWithInvalidJson() throws Exception {
        VerifyOtpRequest verifyOtpRequest = getVerifyOtpRequest("+919494774110",1234,1L);
        VerifyOtpResponse verifyOtpResponse = getVerifyOtp("otp verified",
                getStatus("success",20001));
        Mockito.when(authService.verifyOtp(verifyOtpRequest)).thenReturn(verifyOtpResponse);
        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.VERIFY_OTP)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"number\": \"+919494774110\",\n" +
                                "  \"userId\": 1,\n" +
                                "  \"otp\": \"1234\"\n" +
                                ""))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Unable to process request at this time"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void verifyOtpWithoutInput() throws Exception {
        VerifyOtpRequest verifyOtpRequest = getVerifyOtpRequest("+919494774110",1234,1L);
        VerifyOtpResponse verifyOtpResponse = getVerifyOtp("otp verified",
                getStatus("success",20001));
        Mockito.when(authService.verifyOtp(verifyOtpRequest)).thenReturn(verifyOtpResponse);
        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.VERIFY_OTP)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Unable to process request at this time"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void verifyOtpInvalidContentType() throws Exception {
        VerifyOtpRequest verifyOtpRequest = getVerifyOtpRequest("+919494774110",1234,1L);
        VerifyOtpResponse verifyOtpResponse = getVerifyOtp("otp verified",
                getStatus("success",20001));
        Mockito.when(authService.verifyOtp(verifyOtpRequest)).thenReturn(verifyOtpResponse);
        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.VERIFY_OTP)
                        .contentType(MediaType.APPLICATION_XML)
                        .content(""))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Unable to process request at this time"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void verifyOtpWithMobileNumberNull() throws Exception {
        VerifyOtpRequest verifyOtpRequest = getVerifyOtpRequest(null,1234,1L);
        WithOutDataOrNull(getJson(verifyOtpRequest),ApiUrls.VERIFY_OTP);
    }

    @Test
    void verifyOtpWithOtpNull() throws Exception {
        VerifyOtpRequest verifyOtpRequest = getVerifyOtpRequest("+919791242455",null,1L);
        WithOutDataOrNull(getJson(verifyOtpRequest),ApiUrls.VERIFY_OTP);
    }

    @Test
    void verifyOtpWithAppIdNull() throws Exception {
        VerifyOtpRequest verifyOtpRequest = getVerifyOtpRequest("+919791242455",1234,null);
        WithOutDataOrNull(getJson(verifyOtpRequest),ApiUrls.VERIFY_OTP);
    }

    @Test
    void verifyOtpWithoutMobileNumber() throws Exception {
        String json = "{\n" +
                "  \"appId\": 2,\n" +
                "  \"otp\": 1234\n" +
                "}";
        WithOutDataOrNull(json,ApiUrls.VERIFY_OTP);
    }

    @Test
    void verifyOtpWithoutAppId() throws Exception {
        String json = "{\n" +
                "  \"number\": \"+919497174235\",\n" +
                "  \"otp\": 1234\n" +
                "}";
        WithOutDataOrNull(json,ApiUrls.VERIFY_OTP);
    }

    @Test
    void verifyOtpWithoutOtp() throws Exception {
        String json = "{\n" +
                "  \"number\": \"+919497174235\",\n" +
                "  \"appId\": 2\n" +
                "}";
        WithOutDataOrNull(json,ApiUrls.VERIFY_OTP);
    }

    void WithOutDataOrNull(String json , String apiUrl) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(apiUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Input validation failed"))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }
}