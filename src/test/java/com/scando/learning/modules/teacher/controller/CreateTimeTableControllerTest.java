package com.scando.learning.modules.teacher.controller;

import com.scando.learning.LearningApplication;
import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.modules.teacher.models.rest.CreateTimeTableRequest;
import com.scando.learning.modules.teacher.models.rest.CreateTimeTableResponse;
import com.scando.learning.modules.teacher.models.rest.TimeTableSession;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = LearningApplication.class)
class CreateTimeTableControllerTest extends AbstractTeacherControllerTest {


    @MockBean
    TeacherService teacherService;


    @Test
    void createTimeTableValidData() throws Exception {
        List<TimeTableSession> timeTableSessionList = new ArrayList<>();
        timeTableSessionList.add(getTimeTableSession(setTimeData(), setTimeData(), "01"));
        timeTableSessionList.add(getTimeTableSession(setTimeData(), setTimeData(), "02"));
        timeTableSessionList.add(getTimeTableSession(setTimeData(), setTimeData(), "03"));
        CreateTimeTableRequest createTimeTableRequest = getCreateTimeTableRequest(timeTableSessionList, 1L,
                1L);
        CreateTimeTableResponse createTimeTableResponse = getCreateTimeTableResponse(getStatus(20001),
                "success");


        when(teacherService.createTimeTable(createTimeTableRequest)).thenReturn(createTimeTableResponse);
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiUrls.CREATE_TIMETABLE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(createTimeTableRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message").value("success"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createTimeTableInvalidData() throws Exception {
        List<TimeTableSession> timeTableSessionList = new ArrayList<>();
        timeTableSessionList.add(getTimeTableSession(setTimeData(), setTimeData(), "01"));
        timeTableSessionList.add(getTimeTableSession(setTimeData(), setTimeData(), "02"));
        timeTableSessionList.add(getTimeTableSession(setTimeData(), setTimeData(), "Wed"));

        CreateTimeTableRequest createTimeTableRequest = getCreateTimeTableRequest(timeTableSessionList, null,
                null);
        CreateTimeTableResponse createTimeTableResponse = getCreateTimeTableResponse(getStatus(20001),
                "success");

        when(teacherService.createTimeTable(createTimeTableRequest)).thenReturn(createTimeTableResponse);
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiUrls.CREATE_TIMETABLE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(createTimeTableRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Input validation failed"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createTimeTableInvalidJson() throws Exception {
        List<TimeTableSession> timeTableSessionList = new ArrayList<>();
        timeTableSessionList.add(getTimeTableSession(setTimeData(), setTimeData(), "01"));
        timeTableSessionList.add(getTimeTableSession(setTimeData(), setTimeData(), "02"));
        timeTableSessionList.add(getTimeTableSession(setTimeData(), setTimeData(), "03"));

        CreateTimeTableRequest createTimeTableRequest = getCreateTimeTableRequest(timeTableSessionList, null,
                null);
        CreateTimeTableResponse createTimeTableResponse = getCreateTimeTableResponse(getStatus(20001),
                "success");

        when(teacherService.createTimeTable(createTimeTableRequest)).thenReturn(createTimeTableResponse);
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiUrls.CREATE_TIMETABLE)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("aaaaaaaasldfjdlsfa"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Unable to process request at this time"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createTimeTableEmptyInput() throws Exception {
        List<TimeTableSession> timeTableSessionList = new ArrayList<>();
        timeTableSessionList.add(getTimeTableSession(setTimeData(), setTimeData(), "01"));
        timeTableSessionList.add(getTimeTableSession(setTimeData(), setTimeData(), "02"));
        timeTableSessionList.add(getTimeTableSession(setTimeData(), setTimeData(), "03"));

        CreateTimeTableRequest createTimeTableRequest = getCreateTimeTableRequest(timeTableSessionList, null,
                null);
        CreateTimeTableResponse createTimeTableResponse = getCreateTimeTableResponse(getStatus(20001),
                "success");

        when(teacherService.createTimeTable(createTimeTableRequest)).thenReturn(createTimeTableResponse);
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(ApiUrls.CREATE_TIMETABLE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer &$££17338839×9×9s")
                        .header("debug", false)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(""))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Unable to process request at this time"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}