package com.scando.learning.modules.teacher.controller;

import com.scando.learning.LearningApplication;
import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.common.constants.ScheduleDayType;
import com.scando.learning.common.constants.StatusEnum;
import com.scando.learning.modules.teacher.dao.TeacherDao;
import com.scando.learning.modules.teacher.models.rest.CreateClassRoomRequest;
import com.scando.learning.modules.teacher.models.rest.CreateClassRoomResponse;
import com.scando.learning.modules.teacher.models.rest.Time;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = LearningApplication.class)
public class CreateClassRoomControllerTest extends AbstractTeacherControllerTest{

    @MockBean
    private TeacherService teacherService;

    @MockBean
    private TeacherDao teacherDao;

    @Test
    void createClassRoomTestWithValidData() throws Exception{
        CreateClassRoomRequest createRequest = getCreateClassRequest(
                1 , "10 th standard", "Maths", 1L);

        Mockito.when(teacherService.createClassRoom(createRequest)).thenReturn(new CreateClassRoomResponse());

        when(teacherDao.getTimeTableForClass(any())).thenReturn(getTimeTable());

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.CREATE_CLASSROOM)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                .content(getJson(createRequest))).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void createClassRoomTestWithInvalidData() throws Exception{
        CreateClassRoomRequest createRequest = getCreateClassRequest(null,
                "0 th stabdard ddddddd","Sample subjesssssssssssssssssssssssssssssssssssssss", 1L);

        Mockito.when(teacherService.createClassRoom(createRequest)).thenReturn(new CreateClassRoomResponse());

        when(teacherDao.getTimeTableForClass(any())).thenReturn(getTimeTable());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.CREATE_CLASSROOM)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(createRequest))).andDo(print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));
    }

    @Test
    void createClassRoomTestWithInvalidClassType() throws Exception{
        CreateClassRoomRequest createRequest = getCreateClassRequest(6,
                "0 th Standard","Sample ", 1L);

        Mockito.when(teacherService.createClassRoom(createRequest)).thenReturn(new CreateClassRoomResponse());

        when(teacherDao.getTimeTableForClass(any())).thenReturn(getTimeTable());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.CREATE_CLASSROOM)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(createRequest))).andDo(print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));
    }

    @Test
    void createClassRoomTestWithInvalidDayType() throws  Exception {
        CreateClassRoomRequest createRequest = getInvalidCreateClassRequest(1,
                "0 th standard","Maths", 1L);

        Mockito.when(teacherService.createClassRoom(createRequest)).thenReturn(new CreateClassRoomResponse());

        when(teacherDao.getTimeTableForClass(any())).thenReturn(getTimeTable());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.CREATE_CLASSROOM)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(createRequest))).andDo(print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));
    }

    @Test
    void createClassRoomTestWithInvalidJson() throws Exception{
        CreateClassRoomRequest createRequest = getCreateClassRequest(
                1 , "10 th standard", "Maths", 1L);

        Mockito.when(teacherService.createClassRoom(createRequest)).thenReturn(new CreateClassRoomResponse());

        when(teacherDao.getTimeTableForClass(any())).thenReturn(getTimeTable());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.CREATE_CLASSROOM)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("new")).andDo(print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));

    }

    @Test
    void createClassRoomTestWithoutInput() throws Exception{
        CreateClassRoomRequest createRequest = getCreateClassRequest(
                null , null, null, null);

        Mockito.when(teacherService.createClassRoom(createRequest)).thenReturn(new CreateClassRoomResponse());

        when(teacherDao.getTimeTableForClass(any())).thenReturn(getTimeTable());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.CREATE_CLASSROOM)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("")).andDo(print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));

    }

    @Test
    void editClassRoomTestWithValidData() throws Exception{
        CreateClassRoomRequest createRequest = getCreateClassRequest(
                 1 , "10 th standard", "Maths", 1L);

        Mockito.when(teacherService.createClassRoom(createRequest)).thenReturn(new CreateClassRoomResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(teacherDao.getTimeTableForClass(any())).thenReturn(getTimeTable());

        mockMvc.perform(MockMvcRequestBuilders.put(ApiUrls.EDIT_CLASSROOM, 1L)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("classId", String.valueOf(1L))
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(createRequest))).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void editClassRoomTestWithInvalidData() throws Exception{
        CreateClassRoomRequest createRequest = getCreateClassRequest( 1,
                null,"Sample subjesssssssssssssssssssssssssssssssssssssss", 1L);

        Mockito.when(teacherService.createClassRoom(createRequest)).thenReturn(new CreateClassRoomResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(teacherDao.getTimeTableForClass(any())).thenReturn(getTimeTable());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put(ApiUrls.EDIT_CLASSROOM, 1L)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("classId", String.valueOf(1L))
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(createRequest))).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));
    }

    @Test
    void editClassRoomTestWithInvalidClassType() throws Exception{
        CreateClassRoomRequest createRequest = getCreateClassRequest( 8,
                "English","Sample ", 1L);

        Mockito.when(teacherService.createClassRoom(createRequest)).thenReturn(new CreateClassRoomResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(teacherDao.getTimeTableForClass(any())).thenReturn(getTimeTable());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put(ApiUrls.EDIT_CLASSROOM, 1L)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("classId", String.valueOf(1L))
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(createRequest))).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));
    }

    @Test
    void editClassRoomTestWithInvalidDayType() throws  Exception {
        CreateClassRoomRequest createRequest = getInvalidCreateClassRequest(1,
                "0 th standard","Maths", 1L);

        Mockito.when(teacherService.createClassRoom(createRequest)).thenReturn(new CreateClassRoomResponse());

        when(teacherDao.getTimeTableForClass(any())).thenReturn(getTimeTable());
        when(teacherDao.getTimeTableForClass(any())).thenReturn(getTimeTable());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put(ApiUrls.EDIT_CLASSROOM, 1L)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("classId", String.valueOf(1L))
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(createRequest))).andDo(print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));
    }

    @Test
    void editClassRoomTestWithInvalidJson() throws Exception{
        CreateClassRoomRequest createRequest = getCreateClassRequest(
                1 , "10 th standard", "Maths", 1L);

        Mockito.when(teacherService.createClassRoom(createRequest)).thenReturn(new CreateClassRoomResponse());

        when(teacherDao.getTimeTableForClass(any())).thenReturn(getTimeTable());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put(ApiUrls.EDIT_CLASSROOM, 1L)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .param("classId", String.valueOf(1L))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("new")).andDo(print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));

    }

    @Test
    void editClassRoomTestWithoutInput() throws Exception{
        CreateClassRoomRequest createRequest = getCreateClassRequest(
                null , null, null, null);

        Mockito.when(teacherService.createClassRoom(createRequest)).thenReturn(new CreateClassRoomResponse());

        when(teacherDao.getTimeTableForClass(any())).thenReturn(getTimeTable());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put(ApiUrls.EDIT_CLASSROOM, 1L)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .param("classId", String.valueOf(1L))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("")).andDo(print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));

    }

    protected CreateClassRoomRequest getCreateClassRequest(Integer classType, String className, String subjectName, Long teacherId){
        return CreateClassRoomRequest.builder()
                .classType(classType).className(className).subjectName(subjectName)
                .timetableEnabled(true).timeTable(getTimeTableListRequest())
                .build();
    }

    protected CreateClassRoomRequest getInvalidCreateClassRequest(Integer classType, String className, String subjectName, Long teacherId){
        return CreateClassRoomRequest.builder()
                .classType(classType).className(className).subjectName(subjectName)
                .timetableEnabled(true).timeTable(getInvalidTimeTableListRequest())
                .build();
    }


    protected List<TimeTableSession> getTimeTableListRequest(){
        List<TimeTableSession> timeTableSessionList = new ArrayList<>();

        for (int i=1; i<=1; i++) {
            TimeTableSession timeTableSession = new TimeTableSession();
            timeTableSession.setStart(setTime());
            timeTableSession.setEnd(setTime());
            timeTableSession.setDay(ScheduleDayType.MON.getCode());
            timeTableSessionList.add(timeTableSession);
        }
        return timeTableSessionList;
    }

    protected List<TimeTableSession> getInvalidTimeTableListRequest(){
        List<TimeTableSession> timeTableSessionList = new ArrayList<>();

        for (int i=1; i<=1; i++) {
            TimeTableSession timeTableSession = new TimeTableSession();
            timeTableSession.setStart(setTime());
            timeTableSession.setEnd(setTime());
            timeTableSession.setDay("Monday tuesdat");
            timeTableSessionList.add(timeTableSession);
        }
        return timeTableSessionList;
    }

    protected Time setTime() {
        Time timeData = new Time();
        timeData.setHour("1");
        timeData.setMin("1");
        return  timeData;
    }



}
