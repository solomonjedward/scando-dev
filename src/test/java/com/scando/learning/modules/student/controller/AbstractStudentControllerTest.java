package com.scando.learning.modules.student.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scando.learning.common.exception.ScandoException;
import com.scando.learning.common.models.rest.Status;
import com.scando.learning.modules.auth.model.rest.Login;
import com.scando.learning.modules.student.models.rest.StudentSignUp;
import com.scando.learning.modules.student.models.rest.StudentSignUpRequest;
import com.scando.learning.modules.student.models.rest.StudentSignUpResponse;
import com.scando.learning.modules.teacher.models.ClassEnroll;
import com.scando.learning.modules.teacher.models.ClassRoom;

public class AbstractStudentControllerTest {

    protected String getJson(Object object) throws ScandoException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException var3) {
            throw new ScandoException("Exception while converting object to json", var3);
        }
    }

    protected StudentSignUpRequest getStudentSignUpRequest(String mobileNumber, String userName) {
        return StudentSignUpRequest.builder()
                .number(mobileNumber)
                .userName(userName)
                .appId(1L)
                .build();
    }

    protected StudentSignUpResponse getStudentSignUpResponse(Status status, Login data) {
        return StudentSignUpResponse.builder()
                .status(status)
                .data(data)
                .build();
    }

    protected Status getStatus(Integer code) {
        return Status.builder()
                .statusCode(code)
                .build();
    }

    protected StudentSignUp getStudentSignUp(Long id , String message) {
        return StudentSignUp.builder()
                .studentId(id)
                .message(message)
                .build();
    }

    protected ClassRoom getClassRoom() {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setClassId(1L);
        classRoom.setClassName("10 th Standard");
        classRoom.setSubjectName("Maths");
        classRoom.setTeacherId(1L);
        return classRoom;
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

    protected Login getLogin(String token , String refreshToken , Long userId, Long appId) {
        return Login.builder()
                .token(token)
                .refreshToken(refreshToken)
                .appId(appId)
                .userId(userId)
                .build();
    }

}
