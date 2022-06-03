package com.scando.learning.modules.student.controller;

import com.scando.learning.LearningApplication;
import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.modules.student.models.rest.StudentSignUpRequest;
import com.scando.learning.modules.student.models.rest.StudentSignUpResponse;
import com.scando.learning.modules.student.service.StudentService;
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
class StudentControllerTest extends AbstractStudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void studentSignUpValidData() throws Exception {
        StudentSignUpRequest studentSignUpRequest = getStudentSignUpRequest("+919497174250"
                , "simon");
        StudentSignUpResponse studentSignUpResponse = getStudentSignUpResponse(getStatus(20001),
               getLogin("token","refreshToken",1L, 1L));

        Mockito.when(studentService.studentSignUp(studentSignUpRequest)).thenReturn(studentSignUpResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.CREATE_STUDENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(studentSignUpRequest))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.token")
                        .value("token"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void studentSignUpInvalidValidData() throws Exception {
        StudentSignUpRequest studentSignUpRequest = getStudentSignUpRequest("+91949717250"
                , "simon");
        StudentSignUpResponse studentSignUpResponse = getStudentSignUpResponse(getStatus(20001),
                getLogin("token","refreshToken",1L, 1L));

        Mockito.when(studentService.studentSignUp(studentSignUpRequest)).thenReturn(studentSignUpResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.CREATE_STUDENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(studentSignUpRequest))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Input validation failed"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void studentSignUpInvalidJson() throws Exception {
        StudentSignUpRequest studentSignUpRequest = getStudentSignUpRequest("+91949717250"
                , "simon");
        StudentSignUpResponse studentSignUpResponse = getStudentSignUpResponse(getStatus(20001),
                getLogin("token","refreshToken",1L, 1L));

        Mockito.when(studentService.studentSignUp(studentSignUpRequest)).thenReturn(studentSignUpResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.CREATE_STUDENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("sfldkfakllajflkflak")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Unable to process request at this time"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void studentSignUpWithoutInput() throws Exception {
        StudentSignUpRequest studentSignUpRequest = getStudentSignUpRequest("+91949717250"
                , "simon");
        StudentSignUpResponse studentSignUpResponse = getStudentSignUpResponse(getStatus(20001),
                getLogin("token","refreshToken",1L, 1L));

        Mockito.when(studentService.studentSignUp(studentSignUpRequest)).thenReturn(studentSignUpResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.CREATE_STUDENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Unable to process request at this time"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void studentSignUpinvalidContentType() throws Exception {
        StudentSignUpRequest studentSignUpRequest = getStudentSignUpRequest("+91949717250"
                , "simon");
        StudentSignUpResponse studentSignUpResponse = getStudentSignUpResponse(getStatus(20001),
                getLogin("token","refreshToken",1L, 1L));

        Mockito.when(studentService.studentSignUp(studentSignUpRequest)).thenReturn(studentSignUpResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.CREATE_STUDENT)
                        .contentType(MediaType.APPLICATION_XML)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Unable to process request at this time"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}