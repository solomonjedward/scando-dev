package com.scando.learning.modules.teacher.builder;

import com.scando.learning.common.constants.Constants;
import com.scando.learning.common.constants.StatusEnum;
import com.scando.learning.common.models.User;
import com.scando.learning.common.models.UserLoginInfo;
import com.scando.learning.common.models.rest.PagedData;
import com.scando.learning.common.models.rest.Status;
import com.scando.learning.common.utils.WebUtils;
import com.scando.learning.modules.student.models.rest.ClassDetail;
import com.scando.learning.modules.teacher.models.rest.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class TeacherResponseBuilder {
    public GetTeacherClassesResponse buildGetOwnClassesResponse(List<GetTeacherClassesResponseModel> getOwnClassesResponseModelList) {

        if (getOwnClassesResponseModelList.isEmpty()) {
            GetTeacherClassesResponseModel getOwnClassesResponseModel = new GetTeacherClassesResponseModel();
            getOwnClassesResponseModel.setMessage(Constants.NO_DATA_FOUND);
            getOwnClassesResponseModelList.add(getOwnClassesResponseModel);
            return GetTeacherClassesResponse.builder()
                    .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                    .data(getOwnClassesResponseModelList)
                    .build();
        }

        return GetTeacherClassesResponse.builder()
                .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                .data(getOwnClassesResponseModelList)
                .build();
    }

    public CreateClassRoomResponse createClassRoomResponse(Long classId, String message) {

        return CreateClassRoomResponse.builder()
                .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                .data(CreateClassRoomResponseModel.builder().status(message).classRoomId(classId).build())
                .build();
    }

    public TeacherSignUpResponse buildTeacherSignUpResponse(TeacherSignUpRequest teacherSignUpRequest, User user, UserLoginInfo userLoginInfo, String message) {
        return TeacherSignUpResponse.builder()
                .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                .data(TeacherSignUpResponseModel.builder()
                        .status(message)
                        .code(0)
                        .userId(user.getUserId())
                        .userType(user.getUserType())
                        .userName(teacherSignUpRequest.getUserName())
                        .profileUrl(teacherSignUpRequest.getProfile_url())
                        .appId(userLoginInfo.getAppId())
                        .token(userLoginInfo.getToken())
                        .refreshToken(userLoginInfo.getRefreshToken())
                        .build())
                .build();
    }

    public DeleteClassRoomResponse buildDeleteClassRoomResponse(String message, int code) {
        return DeleteClassRoomResponse.builder()
                .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                .data(DeleteClassRoomResponseModel.builder().message(message).code(code).build())
                .build();
    }

    public GetEnrollListResponse buildGetEnrollRequestListResponse(PagedData<EnrollDetails> enrollRequestList) {
        return GetEnrollListResponse.builder()
                .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                .data(enrollRequestList)
                .build();
    }

    public ApproveClassResponse buildApproveClassResponse(String message, Long classId, Long studentId) {
        return ApproveClassResponse.builder()
                .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                .data(ApproveClassResponseModel.builder().classId(classId).studentId(studentId).message(message).build())
                .build();
    }

    public CreateTimeTableResponse buildCreateTimeTableResponse(String message) {
        Status status = WebUtils.getStatus();
        status.setEndTime(System.currentTimeMillis());
        status.setStatusCode(StatusEnum.SUCCESS.getCode());
        return CreateTimeTableResponse.builder()
                .status(status)
                .data(CreateTimeTableResponseModel.builder().message(message).build())
                .build();
    }

    public GetTeacherClassDetailsResponse buildGetTeacherClassDetailsResponse(List<ClassDetail> classDetailList) {

        if (classDetailList.isEmpty()) {
            ClassDetail classDetail = new ClassDetail();
            classDetail.setMessage(Constants.NO_DATA_FOUND);
            classDetailList.add(classDetail);
            return GetTeacherClassDetailsResponse.builder()
                    .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                    .data(classDetailList)
                    .build();
        }

        return GetTeacherClassDetailsResponse.builder()
                .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                .data(classDetailList)
                .build();
    }

    public GetTimeTableResponse buildGetTimeTableResponse(GetTimeTableResponseModel getTimeTableResponseModels) {
        Status status = WebUtils.getStatus();
        status.setEndTime(System.currentTimeMillis());
        status.setStatusCode(StatusEnum.SUCCESS.getCode());
        return GetTimeTableResponse.builder()
                .status(status)
                .data(getTimeTableResponseModels)
                .build();
    }

    public UnEnrollResponse buildUnEnrollClassResponse(String message, Integer code) {
        return UnEnrollResponse.builder()
                .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                .data(UnEnrollResponseModel.builder().status(message).code(code).build())
                .build();
    }
}
