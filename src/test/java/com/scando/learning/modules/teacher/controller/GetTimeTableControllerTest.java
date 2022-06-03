package com.scando.learning.modules.teacher.controller;

import com.scando.learning.LearningApplication;
import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.modules.teacher.models.rest.GetTimeTableResponse;
import com.scando.learning.modules.teacher.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = LearningApplication.class)
public class GetTimeTableControllerTest extends AbstractTeacherControllerTest {

    @MockBean
    TeacherService teacherService;

    @Test
    void getTimeTableValidData() throws Exception {
        Long classId = 100000001L;
        Long teacherId = 1l;
        GetTimeTableResponse getTimeTableResponse = getTimeTableResponse(getTimeTableResponseModel(classId, teacherId,
                        getTimeTableSessions(3)),
                getStatus(20001));

        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        when(teacherService.getTimeTable(classId)).thenReturn(getTimeTableResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(ApiUrls.GET_TIMETABLE, classId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.classRoomId")
                        .value(100000001))
                .andExpect((MockMvcResultMatchers.status().isOk()));
    }

    @Test
    void getTimeTableDataMoreThan9digit() throws Exception {
        Long classId = 1000000001L;
        Long teacherId = 1l;
        GetTimeTableResponse getTimeTableResponse = getTimeTableResponse(getTimeTableResponseModel(classId, teacherId,
                        getTimeTableSessions(3)),
                getStatus(20001));

        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        when(teacherService.getTimeTable(classId)).thenReturn(getTimeTableResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(ApiUrls.GET_TIMETABLE, classId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Input validation failed"))
                .andExpect((MockMvcResultMatchers.status().isOk()));


    }

    @Test
    void getTimeTableDataLessThan9digit() throws Exception {
        Long classId = 10000002L;
        Long teacherId = 1l;
        GetTimeTableResponse getTimeTableResponse = getTimeTableResponse(getTimeTableResponseModel(classId, teacherId,
                        getTimeTableSessions(3)),
                getStatus(20001));

        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        when(teacherService.getTimeTable(classId)).thenReturn(getTimeTableResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(ApiUrls.GET_TIMETABLE, classId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Input validation failed"))
                .andExpect((MockMvcResultMatchers.status().isOk()));


    }

    @Test
    void getTimeTableWithOutData() throws Exception {
        Long classId = 10000002L;
        Long teacherId = 1l;
        GetTimeTableResponse getTimeTableResponse = getTimeTableResponse(getTimeTableResponseModel(classId, teacherId,
                        getTimeTableSessions(3)),
                getStatus(20001));

        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        when(teacherService.getTimeTable(classId)).thenReturn(getTimeTableResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(ApiUrls.GET_TIMETABLE, "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)).andDo(MockMvcResultHandlers.print())
                .andExpect((MockMvcResultMatchers.status().isNotFound()));


    }

    @Test
    void getTimeTableWithStringData() throws Exception {
        Long classId = 10000002L;
        Long teacherId = 1l;
        GetTimeTableResponse getTimeTableResponse = getTimeTableResponse(getTimeTableResponseModel(classId, teacherId,
                        getTimeTableSessions(3)),
                getStatus(20001));

        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        when(teacherService.getTimeTable(classId)).thenReturn(getTimeTableResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(ApiUrls.GET_TIMETABLE, "added")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.code").value(40004))
                .andExpect((MockMvcResultMatchers.status().isOk()));


    }
}
