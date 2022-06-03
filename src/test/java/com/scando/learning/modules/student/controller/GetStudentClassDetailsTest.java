package com.scando.learning.modules.student.controller;

import com.scando.learning.LearningApplication;
import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.modules.student.models.rest.GetStudentClassDetailResponse;
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
public class GetStudentClassDetailsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void testGetTeacherClassDetailsWithValidData() throws Exception {
        Mockito.when(studentService.getClassDetail( 1L)).thenReturn(new GetStudentClassDetailResponse());

        mockMvc.perform(get(ApiUrls.GET_STUDENT_CLASS_DETAIL_SPECIFIC_CLASS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .param("classId", String.valueOf(2L))
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetTeacherClassDetailsWithInvalidCLassId() throws Exception {
        Mockito.when(studentService.getClassDetail( 1L)).thenReturn(new GetStudentClassDetailResponse());

        mockMvc.perform(get(ApiUrls.GET_STUDENT_CLASS_DETAIL_SPECIFIC_CLASS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("isDebug", false)
                        .param("classId", String.valueOf("a"))
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.statusCode")
                        .value(20002))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetTeacherClassDetailsWithCLassIdNull() throws Exception {
        Mockito.when(studentService.getClassDetail( 1L)).thenReturn(new GetStudentClassDetailResponse());

        mockMvc.perform(get(ApiUrls.GET_STUDENT_CLASS_DETAIL_SPECIFIC_CLASS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("isDebug", false)
                        .param("classId", String.valueOf(""))
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(jsonPath("$.status.statusCode").value(20002))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetTeacherClassDetailsWithOutCLassId() throws Exception {
        Mockito.when(studentService.getClassDetail( 1L)).thenReturn(new GetStudentClassDetailResponse());

        mockMvc.perform(get(ApiUrls.GET_STUDENT_CLASS_DETAIL_SPECIFIC_CLASS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("isDebug", false)
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(jsonPath("$.status.statusCode").value(20002))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
