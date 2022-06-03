package com.scando.learning.modules.student.controller;


import com.scando.learning.LearningApplication;
import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.modules.student.models.rest.GetStudentClassesResponse;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = LearningApplication.class)
public class GetOwnClassStudentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;


    @Test
    void testGetOwnStudentClassWithValidData() throws Exception {
        Mockito.when(studentService.getStudentClassOnSpecificDay(2L, "Mon")).thenReturn(new GetStudentClassesResponse());

        mockMvc.perform(get(ApiUrls.GET_STUDENT_CLASSES_ON_SPECIFIC_DAY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization","Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .param("userId", String.valueOf(2L))
                        .param("day", "Mon")
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetOwnStudentClassWithInvalidUserId() throws Exception {
        Mockito.when(studentService.getStudentClassOnSpecificDay(2L, "Mon")).thenReturn(new GetStudentClassesResponse());

        mockMvc.perform(get(ApiUrls.GET_STUDENT_CLASSES_ON_SPECIFIC_DAY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization","Bearer &$££17338839×9×9s")
                        .header("isDebug", false)
                        .param("userId", String.valueOf("a"))
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.statusCode")
                        .value(20002))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetOwnStudentClassWithUserIdNull() throws Exception {
        Mockito.when(studentService.getStudentClassOnSpecificDay(2L, "Mon")).thenReturn(new GetStudentClassesResponse());

        mockMvc.perform(get(ApiUrls.GET_STUDENT_CLASSES_ON_SPECIFIC_DAY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization","Bearer &$££17338839×9×9s")
                        .header("isDebug", false)
                        .param("userId", String.valueOf(""))
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(jsonPath("$.status.statusCode").value(20002))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetOwnStudentClassWithOutUserId() throws Exception {
        Mockito.when(studentService.getStudentClassOnSpecificDay(2L, "Mon")).thenReturn(new GetStudentClassesResponse());

        mockMvc.perform(get(ApiUrls.GET_STUDENT_CLASSES_ON_SPECIFIC_DAY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization","Bearer &$££17338839×9×9s")
                        .header("isDebug", false)
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(jsonPath("$.status.statusCode").value(20002))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
