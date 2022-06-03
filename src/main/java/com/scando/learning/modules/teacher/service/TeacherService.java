package com.scando.learning.modules.teacher.service;

import com.scando.learning.common.models.rest.Status;
import com.scando.learning.modules.teacher.models.rest.*;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {

    TeacherSignUpResponse teacherSignUp(TeacherSignUpRequest teacherSignUpRequest);

    CreateClassRoomResponse createClassRoom(CreateClassRoomRequest createClassRoomRequest);

    CreateClassRoomResponse editClassRoom(CreateClassRoomRequest editClassRequest, Long classId);

    DeleteClassRoomResponse deleteClassRoom(DeleteClassRoomRequest deleteClassRoomRequest);

    CreateTimeTableResponse createTimeTable(CreateTimeTableRequest createTimeTableRequest);


    ApproveClassResponse approveClass(ApproveClassRequest approveClassRequest);

    GetTimeTableResponse getTimeTable(Long classRoomId);

    GetClassRoomResponse getClassRoom(Long classId);

    GetTeacherClassesResponse getOwnClasses(Long userId, Long isScheduled);

    UnEnrollResponse unEnrollClass(EnrollClassRequest enrollClassRequest);

    GetEnrollListResponse getApprovalList(GetEnrollListRequest enrollListRequest);

    GetTeacherClassesResponse getOwnClassesOnSpecificDay(Long userId, String day);

    GetTeacherClassDetailsResponse getSpecificClassDetails(Long classId);

    GetTeacherClassDetailsResponse getAllClassDetails(Long userId,Long isScheduled);
}
