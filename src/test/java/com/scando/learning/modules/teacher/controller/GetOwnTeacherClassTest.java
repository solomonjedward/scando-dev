package com.scando.learning.modules.teacher.controller;

import com.scando.learning.LearningApplication;
import com.scando.learning.common.config.interceptor.SecurityInterceptor;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = LearningApplication.class)
public class GetOwnTeacherClassTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @MockBean
    SecurityInterceptor securityInterceptor;

    @Test
    void testGetOwnTeacherClassWithValidData() throws Exception {
        Mockito.when(teacherService.getOwnClassesOnSpecificDay(2L, "Mon")).thenReturn(new GetTeacherClassesResponse());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(get(ApiUrls.GET_TEACHER_OWN_CLASSES_ON_SPECIFIC_DAY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization","Bearer &$££17338839×9×9s")
                        .header("isDebug", false)
                        .param("userId", String.valueOf(2L))
                        .param("day", "Mon")
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetOwnTeacherClassWithInvalidUserId() throws Exception {
        Mockito.when(teacherService.getOwnClassesOnSpecificDay(2L, "Mon")).thenReturn(new GetTeacherClassesResponse());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(get(ApiUrls.GET_TEACHER_OWN_CLASSES_ON_SPECIFIC_DAY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", String.valueOf("a"))
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(jsonPath("$.status.statusCode").value(20002))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetOwnTeacherClassWithUserIdNull() throws Exception {
        Mockito.when(teacherService.getOwnClassesOnSpecificDay(2L, "Mon")).thenReturn(new GetTeacherClassesResponse());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(get(ApiUrls.GET_TEACHER_OWN_CLASSES_ON_SPECIFIC_DAY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", String.valueOf(""))
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(jsonPath("$.status.statusCode").value(20002))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetOwnTeacherClassWithOutUserId() throws Exception {
        Mockito.when(teacherService.getOwnClassesOnSpecificDay(2L, "Mon")).thenReturn(new GetTeacherClassesResponse());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(get(ApiUrls.GET_TEACHER_OWN_CLASSES_ON_SPECIFIC_DAY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(jsonPath("$.status.statusCode").value(20002))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
