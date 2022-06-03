package com.scando.learning.modules.teacher.controller;

import com.scando.learning.LearningApplication;
import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.modules.teacher.models.rest.GetTeacherClassesResponse;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = LearningApplication.class)
public class GetTeacherAllClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @Test
    void testGetTeacherAllClassWithValidData() throws Exception {
        Mockito.when(teacherService.getOwnClasses(2L,0L)).thenReturn(new GetTeacherClassesResponse());

        mockMvc.perform(get(ApiUrls.GET_TEACHER_ALL_CLASSES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization","Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .param("userId", String.valueOf(2L))
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetTeacherAllClassWithInvalidUserId() throws Exception {
        Mockito.when(teacherService.getOwnClasses(2L,0L)).thenReturn(new GetTeacherClassesResponse());

        mockMvc.perform(get(ApiUrls.GET_TEACHER_ALL_CLASSES)
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
    void testGetTeacherAllClassWithUserIdNull() throws Exception {
        Mockito.when(teacherService.getOwnClasses(2L,0L)).thenReturn(new GetTeacherClassesResponse());

        mockMvc.perform(get(ApiUrls.GET_TEACHER_ALL_CLASSES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization","Bearer &$££17338839×9×9s")
                        .header("isDebug", false)
                        .param("userId", String.valueOf(""))
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(jsonPath("$.status.statusCode").value(20002))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetTeacherAllClassWithOutUserId() throws Exception {
        Mockito.when(teacherService.getOwnClasses(2L,0L)).thenReturn(new GetTeacherClassesResponse());

        mockMvc.perform(get(ApiUrls.GET_TEACHER_ALL_CLASSES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization","Bearer &$££17338839×9×9s")
                        .header("isDebug", false)
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(jsonPath("$.status.statusCode").value(20002))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
