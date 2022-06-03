package com.scando.learning.modules.teacher.controller;

import com.scando.learning.common.ErrorResponseBuilder.ResponseBuilder;
import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.common.constants.ErrorCodeEnum;
import com.scando.learning.common.exception.ControllerException;
import com.scando.learning.common.exception.ServiceException;
import com.scando.learning.common.models.SwaggerHeads;
import com.scando.learning.common.models.rest.Status;
import com.scando.learning.common.utils.WebUtils;
import com.scando.learning.modules.teacher.models.rest.*;
import com.scando.learning.modules.teacher.service.TeacherService;
import com.scando.learning.modules.teacher.validator.TeacherValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@Api(tags = {SwaggerHeads.TEACHER_API})
@Slf4j
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    ResponseBuilder responseBuilder;

    @Autowired
    TeacherValidator teacherValidator;

    @PostMapping(ApiUrls.CREATE_CLASSROOM)
    @ApiOperation(value = "Create ClassRoom")
    public ResponseEntity<CreateClassRoomResponse> createClassRoom(@RequestBody @Valid CreateClassRoomRequest createClassRoomRequest, BindingResult bindingResult) {
        try {
            Status status = WebUtils.getStatus();
            status.setApiId(6);
            WebUtils.setStatus(status);
            if (bindingResult.hasErrors()) {
                LOGGER.debug("Input validation failed");
                throw new ControllerException(bindingResult, ErrorCodeEnum.CREATE_CLASSROOM_VALIDATION);
            }
            teacherValidator.validateClassTypes(createClassRoomRequest.getClassType());

            if (createClassRoomRequest.isTimetableEnabled()) {
                teacherValidator.validateClassSchedules(createClassRoomRequest.getTimeTable());
            }

            CreateClassRoomResponse apiResponse = teacherService.createClassRoom(createClassRoomRequest);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            LOGGER.error(Arrays.toString(e.getStackTrace()));
            throw e;
        }

    }

    @PutMapping(ApiUrls.EDIT_CLASSROOM)
    @ApiOperation(value = "Edit classRoom")
    public ResponseEntity<CreateClassRoomResponse> editClassRoom(@RequestBody @Valid CreateClassRoomRequest editRequest, @PathVariable @Valid Long classId, BindingResult bindingResult) {
        try {
            Status status = WebUtils.getStatus();
            status.setApiId(7);
            WebUtils.setStatus(status);
            if (bindingResult.hasErrors()) {
                LOGGER.error("Input validation failed");
                throw new ControllerException(bindingResult, ErrorCodeEnum.CREATE_CLASSROOM_VALIDATION);
            }
            teacherValidator.validateClassTypes(editRequest.getClassType());

            if (editRequest.isTimetableEnabled()) {
                teacherValidator.validateClassSchedules(editRequest.getTimeTable());
            }
            CreateClassRoomResponse apiResponse = teacherService.editClassRoom(editRequest, classId);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            LOGGER.error(Arrays.toString(e.getStackTrace()));
            throw e;
        }
    }

    @DeleteMapping(ApiUrls.DELETE_CLASSROOM)
    @ApiOperation(value = "DELETE ClassRoom")
    public ResponseEntity<DeleteClassRoomResponse> deleteClassRoom(@RequestBody @Valid DeleteClassRoomRequest deleteClassRoomRequest, BindingResult bindingResult) {
        try {
            Status status = WebUtils.getStatus();
            status.setApiId(10);
            WebUtils.setStatus(status);
            if (bindingResult.hasErrors()) {
                LOGGER.error("Input validation failed");
                throw new ControllerException(bindingResult, ErrorCodeEnum.DELETE_CLASS_VALIDATION_FAILED);
            }

            DeleteClassRoomResponse apiResponse = teacherService.deleteClassRoom(deleteClassRoomRequest);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            LOGGER.error(Arrays.toString(e.getStackTrace()));
            throw e;
        }
    }

    @PostMapping(ApiUrls.CREATE_TIMETABLE)
    @ApiOperation(value = "Create/update TimeTable")
    public ResponseEntity<CreateTimeTableResponse> createTimeTable(
            @RequestBody @Valid CreateTimeTableRequest createTimeTableRequest, BindingResult bindingResult) {
        Status status = WebUtils.getStatus();
        status.setApiId(11);
        WebUtils.setStatus(status);
        if (bindingResult.hasErrors()) {
            LOGGER.debug("Input validation failed for {}", CreateTimeTableRequest.class);
            throw new ControllerException(
                    bindingResult,
                    ErrorCodeEnum.CREATE_TIME_TABLE_VALIDATION_FAILED
            );
        }
        teacherValidator.validateClassSchedules(createTimeTableRequest.getTimeTable());
        try {
            CreateTimeTableResponse apiResponse = teacherService.createTimeTable(createTimeTableRequest);
            return ResponseEntity.ok(apiResponse);
        } catch (ServiceException ex) {
            throw new ServiceException(ex.getErrorCode());
        }

    }

    @GetMapping(ApiUrls.GET_TIMETABLE)
    @ApiOperation(value = "Get TimeTable")
    public ResponseEntity<GetTimeTableResponse> getTimeTable(@PathVariable("classId") Long classRoomId
    ) {
        Status status = WebUtils.getStatus();
        status.setApiId(18);
        WebUtils.setStatus(status);
        if (classRoomId > 999999999 || classRoomId < 100000000) {
            LOGGER.debug("Input validation failed for Get timeTable");
            throw new ControllerException(
                    null,
                    ErrorCodeEnum.GET_TIMETABLE_INPUTVALIDATION_FAILED
            );
        }

        GetTimeTableResponse apiResponse = teacherService.getTimeTable(classRoomId);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(ApiUrls.CREATE_TEACHER)
    @ApiOperation(value = "Teacher Create")
    public ResponseEntity<TeacherSignUpResponse> teacherSignUp(@RequestBody @Valid TeacherSignUpRequest teacherSignUpRequest, BindingResult bindingResult) {
        try {
            Status status = WebUtils.getStatus();
            status.setApiId(3);
            WebUtils.setStatus(status);
            if (bindingResult.hasErrors()) {
                LOGGER.debug("INPUT VALIDATION FAILED");
                throw new ControllerException(
                        bindingResult,
                        ErrorCodeEnum.CREATE_TEACHER_INPUT_VALIDATION_FAILED
                );
            }
            teacherValidator.ValidateCreateTeacherRequest(teacherSignUpRequest, status);
            TeacherSignUpResponse apiResponse = teacherService.teacherSignUp(teacherSignUpRequest);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @GetMapping(ApiUrls.GET_TEACHER_OWN_CLASSES_ON_SPECIFIC_DAY)
    @ApiOperation(value = "get own classes of a teacher")
    public ResponseEntity<GetTeacherClassesResponse> getOwnClasses(@RequestParam("userId") Long userId, @RequestParam("day") String day) {
        try {
            Status status = new Status();
            status.setApiId(3);
            teacherValidator.getOwnClassesRequestValidator(userId, day, status);
            final GetTeacherClassesResponse getOwnClassesResponse = teacherService.getOwnClassesOnSpecificDay(userId, day);
            return ResponseEntity.ok(getOwnClassesResponse);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @PostMapping(ApiUrls.UN_ENROLL_STUDENT)
    @ApiOperation(value = "Un enroll the student from a class")
    public ResponseEntity<UnEnrollResponse> unEnrollStudent(@RequestBody @Valid EnrollClassRequest enrollClassRequest, BindingResult bindingResult) {
        try {
            Status status = new Status();
            status.setApiId(25);
            if (bindingResult.hasErrors()) {
                LOGGER.error("Input validation failed");
                throw new ControllerException(bindingResult, ErrorCodeEnum.TEACHER_UN_ENROLL_VALIDATION_FAILED);
            }

            UnEnrollResponse apiResponse = teacherService.unEnrollClass(enrollClassRequest);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @PostMapping(ApiUrls.APPROVE_REQUEST)
    @ApiOperation(value = "Approve  student for a class")
    public ResponseEntity<ApproveClassResponse> approveRequest(@RequestBody @Valid ApproveClassRequest approveClassRequest, BindingResult bindingResult) {
        try {
            Status status = WebUtils.getStatus();
            status.setApiId(15);
            WebUtils.setStatus(status);
            if (bindingResult.hasErrors()) {
                LOGGER.error("Input validation failed");
                throw new ControllerException(bindingResult, ErrorCodeEnum.APPROVE_REQUEST_VALIDATION_FAILED);
            }

            ApproveClassResponse apiResponse = teacherService.approveClass(approveClassRequest);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            LOGGER.error(Arrays.toString(e.getStackTrace()));
            throw e;
        }
    }

    @GetMapping(ApiUrls.GET_APPROVAL_LIST)
    @ApiOperation(value = "get all class enroll requests or a specific class enroll request")
    public ResponseEntity<GetEnrollListResponse> getApprovalList(@ModelAttribute @Valid GetEnrollListRequest enrollListRequest, BindingResult bindingResult) {
        try {
            Status status = WebUtils.getStatus();
            status.setApiId(14);
            WebUtils.setStatus(status);
            if (bindingResult.hasErrors()) {
                LOGGER.error("Input validation failed");
                throw new ControllerException(bindingResult, ErrorCodeEnum.GET_ENROLL_REQUEST_LIST_VALIDATION_FAILED);
            }

            if (enrollListRequest.getEnrollStatus()!=null) {
                teacherValidator.validateEnrollListRequestStatus(enrollListRequest.getEnrollStatus());
            }

            GetEnrollListResponse apiResponse = teacherService.getApprovalList(enrollListRequest);
            return ResponseEntity.ok(apiResponse);
        } catch (ServiceException e) {
            throw new ServiceException(e.getErrorCode());
        }
    }

    @GetMapping(ApiUrls.GET_TEACHER_ALL_CLASSES)
    @ApiOperation(value = "get all classes of a Teacher ")
    public ResponseEntity<GetTeacherClassesResponse> getAllClasses(@RequestParam("userId") Long userId,
                                                                   @RequestParam(value = "isScheduled", required = false) Long isScheduled) {
        try {
            Status status = new Status();
            status.setApiId(3);
            teacherValidator.getAllClassesRequestValidator(userId, isScheduled, status);
            final GetTeacherClassesResponse getOwnClassesResponse = teacherService.getOwnClasses(userId, isScheduled);
            return ResponseEntity.ok(getOwnClassesResponse);
        } catch (Exception exception) {
            throw exception;
        }
    }


    @GetMapping(ApiUrls.GET_TEACHER_CLASS_DETAIL_SPECIFIC_CLASS)
    @ApiOperation(value = "get details of specific class")
    public ResponseEntity<GetTeacherClassDetailsResponse> getClassDetails(@RequestParam("classId") Long classId) {
        try {
            Status status = new Status();
            status.setApiId(3);
            teacherValidator.getSpecificClassDetailsRequestValidator(classId, status);
            final GetTeacherClassDetailsResponse getTeacherClassDetailsResponse = teacherService.getSpecificClassDetails(classId);
            return ResponseEntity.ok(getTeacherClassDetailsResponse);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @GetMapping(ApiUrls.GET_TEACHER_ALL_CLASS_DETAILS)
    @ApiOperation(value = "get details of all class")
    public ResponseEntity<GetTeacherClassDetailsResponse> getAllClassDetails(@RequestParam("userId") Long userId,
                                                                             @RequestParam(value = "isScheduled", required = false) Long isScheduled) {
        try {
            Status status = new Status();
            status.setApiId(3);
            teacherValidator.getAllClassDetailsRequestValidator(userId, isScheduled, status);
            final GetTeacherClassDetailsResponse getTeacherClassDetailsResponse = teacherService.getAllClassDetails(userId, isScheduled);
            return ResponseEntity.ok(getTeacherClassDetailsResponse);
        } catch (Exception exception) {
            throw exception;
        }
    }
}
