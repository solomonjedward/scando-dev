package com.scando.learning.modules.teacher.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scando.learning.common.config.interceptor.SecurityInterceptor;
import com.scando.learning.common.exception.ScandoException;
import com.scando.learning.common.models.rest.Status;
import com.scando.learning.modules.teacher.models.ClassEnroll;
import com.scando.learning.modules.teacher.models.ClassRoom;
import com.scando.learning.modules.teacher.models.ScheduledClass;
import com.scando.learning.modules.teacher.models.rest.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class AbstractTeacherControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    SecurityInterceptor securityInterceptor;

    protected String getJson(Object object) throws ScandoException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException var3) {
            throw new ScandoException("Exception while converting object to json", var3);
        }
    }

    protected ClassRoom getClassRoom() {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setClassId(1L);
        classRoom.setClassName("10 th Standard");
        classRoom.setSubjectName("Maths");
        return classRoom;
    }

    protected ScheduledClass getTimeTable() {
        ScheduledClass timeTable = new ScheduledClass();
        timeTable.setTeacherId(1L);
        timeTable.setClassRoomId(1L);
        return timeTable;
    }

    protected ClassEnroll getClassEnrollData() {
        ClassEnroll classEnroll = new ClassEnroll();
        classEnroll.setClassId(1L);
        classEnroll.setEnrollStatus(0);
        classEnroll.setTeacherId(1L);
        classEnroll.setStudentId(1L);
        classEnroll.setEnrollTime(System.currentTimeMillis());
        return classEnroll;
    }

    protected CreateTimeTableRequest getCreateTimeTableRequest(List<TimeTableSession> timeTableSessionList,
                                                               Long classId,
                                                               Long teacherId) {
        return CreateTimeTableRequest.builder()
                .classId(classId)
                .teacherId(teacherId)
                .timeTable(timeTableSessionList)
                .build();
    }

    protected CreateTimeTableResponse getCreateTimeTableResponse(Status status , String message) {
        return CreateTimeTableResponse.builder()
                .status(status)
                .data(CreateTimeTableResponseModel.builder()
                        .message(message)
                        .build())
                .build();
    }

    protected TimeTableSession getTimeTableSession(Time startTime, Time endTime, String day) {
        return TimeTableSession.builder()
                .start(startTime)
                .end(endTime)
                .day(day)
                .build();

    }

    protected GetTimeTableResponseModel getTimeTableResponseModel(Long classId, Long teacherId,
                                                                  List<TimeTableSession> timeTableSession) {
        return GetTimeTableResponseModel.builder()
                .teacherId(teacherId)
                .classRoomId(classId)
                .timeTable(timeTableSession)
                .build();
    }

    protected GetTimeTableResponse getTimeTableResponse(GetTimeTableResponseModel getTimeTableResponseModel ,
                                                        Status status) {
        return GetTimeTableResponse.builder()
                .status(status)
                .data(getTimeTableResponseModel)
                .build();
    }

    protected Status getStatus(Integer code) {
        return Status.builder()
                .statusCode(code)
                .build();
    }

    protected List<ClassEnroll> getClassEnrollList(){
        List<ClassEnroll> classEnrollList = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            ClassEnroll classEnroll = new ClassEnroll();
            classEnroll.setEnrollId(1L);
            classEnroll.setClassId(1L);
            classEnroll.setEnrollStatus(0);
            classEnroll.setTeacherId(1L);
            classEnroll.setStudentId(1L);
            classEnroll.setEnrollTime(System.currentTimeMillis());
        }
        return classEnrollList;
    }

    protected Time setTimeData() {
        Time timeData = new Time();
        timeData.setHour("1");
        timeData.setMin("1");
        return  timeData;
    }

    protected List<TimeTableSession> getTimeTableSessions(Integer count) {
        List<TimeTableSession> timeTableSessions = new ArrayList<>();
        for(int i=0 ; i < count; i++) {
            timeTableSessions.add(
                    getTimeTableSession(setTimeData(),setTimeData(), "0"+i+1)
            );
        }
        return timeTableSessions;
    }

}
