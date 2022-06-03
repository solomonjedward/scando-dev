package com.scando.learning.modules.teacher.controller;

import com.scando.learning.LearningApplication;
import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.common.models.Otp;
import com.scando.learning.common.models.User;
import com.scando.learning.modules.auth.dao.UserDao;
import com.scando.learning.modules.teacher.builder.TeacherResponseBuilder;
import com.scando.learning.modules.teacher.models.rest.TeacherSignUpRequest;
import com.scando.learning.modules.teacher.models.rest.TeacherSignUpResponse;
import com.scando.learning.modules.teacher.service.TeacherService;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = LearningApplication.class)
public class CreateTeacherControllerTest extends AbstractTeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeacherResponseBuilder teacherResponseBuilder;

    @MockBean
    private TeacherService teacherService;

    @MockBean
    private UserDao userDao;

    @Test
    void createTeacherWithValidData() throws Exception {

        TeacherSignUpRequest signUpRequest = setTeacherSignUpRequest();
        Mockito.when(userDao.getUserByNumber(Mockito.any())).thenReturn(null);
        Mockito.when(userDao.getExistingOtp(Mockito.any())).thenReturn(new Otp());



        Mockito.when(teacherService.teacherSignUp(signUpRequest)).thenReturn(new TeacherSignUpResponse());
        Mockito.when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiUrls.CREATE_TEACHER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(signUpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createTeacherWithInValidNumber() throws Exception {

        TeacherSignUpRequest signUpRequest = setTeacherSignUpRequest();
        signUpRequest.setNumber("9544609612");
        Mockito.when(userDao.getUserByNumber(Mockito.any())).thenReturn(null);
        Mockito.when(userDao.getExistingOtp(Mockito.any())).thenReturn(new Otp());



        Mockito.when(teacherService.teacherSignUp(signUpRequest)).thenReturn(new TeacherSignUpResponse());
        Mockito.when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiUrls.CREATE_TEACHER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(signUpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Input validation failed"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createTeacherWithInValidUserType() throws Exception {

        TeacherSignUpRequest signUpRequest = setTeacherSignUpRequest();
        signUpRequest.setUserType(1);
        Mockito.when(userDao.getUserByNumber(Mockito.any())).thenReturn(null);
        Mockito.when(userDao.getExistingOtp(Mockito.any())).thenReturn(new Otp());



        Mockito.when(teacherService.teacherSignUp(signUpRequest)).thenReturn(new TeacherSignUpResponse());
        Mockito.when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiUrls.CREATE_TEACHER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(signUpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Invalid userType for Teacher "))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createTeacherWithInValidSubjectCode() throws Exception {

        TeacherSignUpRequest signUpRequest = setTeacherSignUpRequest();
        signUpRequest.setSubjectCode(new Long[]{801L});
        Mockito.when(userDao.getUserByNumber(Mockito.any())).thenReturn(null);
        Mockito.when(userDao.getExistingOtp(Mockito.any())).thenReturn(new Otp());



        Mockito.when(teacherService.teacherSignUp(signUpRequest)).thenReturn(new TeacherSignUpResponse());
        Mockito.when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiUrls.CREATE_TEACHER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(signUpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("SubjectCode : 801 is invalid"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createTeacherWithExistingNumber() throws Exception {

        TeacherSignUpRequest signUpRequest = setTeacherSignUpRequest();
        Mockito.when(userDao.getUserByNumber(Mockito.any())).thenReturn(new User());
        Mockito.when(userDao.getExistingOtp(Mockito.any())).thenReturn(new Otp());



        Mockito.when(teacherService.teacherSignUp(signUpRequest)).thenReturn(new TeacherSignUpResponse());
        Mockito.when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiUrls.CREATE_TEACHER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(signUpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Phone number Already Exists "))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createTeacherWithoutOtpVerification() throws Exception {

        TeacherSignUpRequest signUpRequest = setTeacherSignUpRequest();
        Mockito.when(userDao.getUserByNumber(Mockito.any())).thenReturn(null);
        Mockito.when(userDao.getExistingOtp(Mockito.any())).thenReturn(null);



        Mockito.when(teacherService.teacherSignUp(signUpRequest)).thenReturn(new TeacherSignUpResponse());
        Mockito.when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiUrls.CREATE_TEACHER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(signUpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Phone number not verified "))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createTeacherWithUserNameNull() throws Exception {

        TeacherSignUpRequest signUpRequest = setTeacherSignUpRequest();
        signUpRequest.setUserName(null);
        Mockito.when(userDao.getUserByNumber(Mockito.any())).thenReturn(null);
        Mockito.when(userDao.getExistingOtp(Mockito.any())).thenReturn(null);



        Mockito.when(teacherService.teacherSignUp(signUpRequest)).thenReturn(new TeacherSignUpResponse());
        Mockito.when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiUrls.CREATE_TEACHER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(signUpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Input validation failed"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void createTeacherWithUserTypeNull() throws Exception {

        TeacherSignUpRequest signUpRequest = setTeacherSignUpRequest();
        signUpRequest.setUserType(null);
        Mockito.when(userDao.getUserByNumber(Mockito.any())).thenReturn(null);
        Mockito.when(userDao.getExistingOtp(Mockito.any())).thenReturn(null);



        Mockito.when(teacherService.teacherSignUp(signUpRequest)).thenReturn(new TeacherSignUpResponse());
        Mockito.when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiUrls.CREATE_TEACHER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(signUpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Input validation failed"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createTeacherWithUserStatusNull() throws Exception {

        TeacherSignUpRequest signUpRequest = setTeacherSignUpRequest();
        signUpRequest.setUserStatus(null);
        Mockito.when(userDao.getUserByNumber(Mockito.any())).thenReturn(null);
        Mockito.when(userDao.getExistingOtp(Mockito.any())).thenReturn(null);



        Mockito.when(teacherService.teacherSignUp(signUpRequest)).thenReturn(new TeacherSignUpResponse());
        Mockito.when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiUrls.CREATE_TEACHER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(signUpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Input validation failed"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createTeacherWithUserProfileUrlNull() throws Exception {

        TeacherSignUpRequest signUpRequest = setTeacherSignUpRequest();
        signUpRequest.setProfile_url(null);
        Mockito.when(userDao.getUserByNumber(Mockito.any())).thenReturn(null);
        Mockito.when(userDao.getExistingOtp(Mockito.any())).thenReturn(null);



        Mockito.when(teacherService.teacherSignUp(signUpRequest)).thenReturn(new TeacherSignUpResponse());
        Mockito.when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiUrls.CREATE_TEACHER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(signUpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Input validation failed"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createTeacherWithSubjectCodeNull() throws Exception {

        TeacherSignUpRequest signUpRequest = setTeacherSignUpRequest();
        signUpRequest.setSubjectCode(null);
        Mockito.when(userDao.getUserByNumber(Mockito.any())).thenReturn(null);
        Mockito.when(userDao.getExistingOtp(Mockito.any())).thenReturn(null);



        Mockito.when(teacherService.teacherSignUp(signUpRequest)).thenReturn(new TeacherSignUpResponse());
        Mockito.when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiUrls.CREATE_TEACHER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(signUpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Input validation failed"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createTeacherWithNumberNull() throws Exception {

        TeacherSignUpRequest signUpRequest = setTeacherSignUpRequest();
        signUpRequest.setNumber(null);
        Mockito.when(userDao.getUserByNumber(Mockito.any())).thenReturn(null);
        Mockito.when(userDao.getExistingOtp(Mockito.any())).thenReturn(null);



        Mockito.when(teacherService.teacherSignUp(signUpRequest)).thenReturn(new TeacherSignUpResponse());
        Mockito.when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiUrls.CREATE_TEACHER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(signUpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Input validation failed"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    private TeacherSignUpRequest setTeacherSignUpRequest() {
        TeacherSignUpRequest teacherSignUpRequest = new TeacherSignUpRequest();
        teacherSignUpRequest.setNumber("+919544609612");
        teacherSignUpRequest.setUserName("testName");
        teacherSignUpRequest.setProfile_url("www.google.com");
        teacherSignUpRequest.setSubjectCode(new Long[]{7001L});
        teacherSignUpRequest.setUserType(0);
        teacherSignUpRequest.setUserStatus(1);

        return teacherSignUpRequest;
    }
}
