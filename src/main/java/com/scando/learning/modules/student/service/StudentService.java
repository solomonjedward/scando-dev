package com.scando.learning.modules.student.service;


import com.scando.learning.modules.student.models.rest.*;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {


    StudentSignUpResponse studentSignUp(StudentSignUpRequest studentSignUpRequest);

    EnrollClassResponse enrollClass(EnrollClassRequest enrollClassRequest);

    CheckLiveClassResponse liveClassCheck(Long classId);

    GetTimetableResponse getTimeTable(Long userId);

    GetStudentClassDetailResponse getClassDetail(Long classId);

    GetEnrolledClassListResponse getEnrolledClass(Long userId);

    UnEnrollResponse unEnrollClass(EnrollClassRequest enrollClassRequest);

    GetStudentClassesResponse getStudentClassOnSpecificDay(Long userId, String day);

    GetStudentClassDetailResponse getAllClassDetail(Long userId, Long isScheduled);

    GetEnrollListResponse getEnrollList(GetEnrollListRequest getEnrollListRequest);
}
