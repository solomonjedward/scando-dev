package com.scando.learning.modules.student.builder;

import com.scando.learning.common.constants.Constants;
import com.scando.learning.common.constants.StatusEnum;
import com.scando.learning.common.models.rest.PagedData;
import com.scando.learning.common.models.rest.Status;
import com.scando.learning.common.utils.WebUtils;
import com.scando.learning.modules.auth.model.rest.Check;
import com.scando.learning.modules.auth.model.rest.CheckResponse;
import com.scando.learning.modules.auth.model.rest.Login;
import com.scando.learning.modules.student.models.rest.*;
import com.scando.learning.modules.teacher.models.rest.GetTeacherClassesResponse;
import com.scando.learning.modules.teacher.models.rest.GetTeacherClassesResponseModel;
import com.scando.learning.modules.teacher.models.rest.GetTimeTableResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class StudentResponseBuilder {

    public StudentSignUpResponse buildStudentSignupResponse(Long appId, Long userId, Integer userType, String token,
                                                            String refreshToken, String profileUrl, Integer code ,
                                                            String userName) {
        return StudentSignUpResponse.builder()
                .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                .data(Login.builder()
                        .token(token)
                        .refreshToken(refreshToken)
                        .appId(appId)
                        .userId(userId)
                        .userType(userType)
                        .userName(userName)
                        .code(code)
                        .profileUrl(profileUrl)
                        .build())
                .build();
    }

    public GetStudentClassesResponse buildGetStudentClassesResponse(List<GetStudentClassesResponseModel> getStudentClassesResponseModelList) {
        if (getStudentClassesResponseModelList.isEmpty()) {
            GetStudentClassesResponseModel getStudentClassesResponseModel = new GetStudentClassesResponseModel();
            getStudentClassesResponseModel.setMessage(Constants.NO_DATA_FOUND);
            getStudentClassesResponseModelList.add(getStudentClassesResponseModel);
            return GetStudentClassesResponse.builder()
                    .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                    .data(getStudentClassesResponseModelList)
                    .build();
        }
        return GetStudentClassesResponse.builder()
                .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                .data(getStudentClassesResponseModelList)
                .build();
    }

    public EnrollClassResponse buildEnrollClassResponse(Long enrollId, String message) {
        return EnrollClassResponse.builder()
                .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                .data(EnrollClass.builder().code(StatusEnum.SUCCESS.getCode())
                        .enrollId(enrollId).message(message).build())
                .build();
    }

    public UnEnrollResponse buildUnEnrollClassResponse(Long enrollId, String message) {
        return UnEnrollResponse.builder()
                .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                .data(UnEnroll.builder().code(StatusEnum.SUCCESS.getCode())
                        .enrollId(enrollId).message(message).build())
                .build();
    }

    public GetStudentClassDetailResponse buildGetStudentClassDetailResponse(List<ClassDetail> classDetailList) {

        if (classDetailList.isEmpty()) {
            ClassDetail classDetail = new ClassDetail();
            classDetail.setMessage(Constants.NO_DATA_FOUND);
            classDetailList.add(classDetail);
            return GetStudentClassDetailResponse.builder()
                    .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                    .data(classDetailList)
                    .build();
        }
        return GetStudentClassDetailResponse.builder()
                .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                .data(classDetailList)
                .build();
    }

    public GetTimetableResponse buildGetTimeTableResponse(GetTimeTableResponseModel getTimeTableResponseModels) {
        Status status = WebUtils.getStatus();
        status.setEndTime(System.currentTimeMillis());
        status.setStatusCode(StatusEnum.SUCCESS.getCode());
        return GetTimetableResponse.builder()
                .status(status)
                .data(getTimeTableResponseModels)
                .build();
    }

    public GetEnrollListResponse buildEnrollListResponse(PagedData<StudentEnrollDetails> enrollList) {
        return GetEnrollListResponse.builder()
                .status(Status.builder().statusCode(StatusEnum.SUCCESS.getCode()).build())
                .data(enrollList)
                .build();
    }
}
