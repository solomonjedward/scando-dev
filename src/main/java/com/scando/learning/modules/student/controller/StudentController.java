package com.scando.learning.modules.student.controller;

import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.common.constants.ErrorCodeEnum;
import com.scando.learning.common.exception.ControllerException;
import com.scando.learning.common.exception.ServiceException;
import com.scando.learning.common.models.SwaggerHeads;
import com.scando.learning.common.ErrorResponseBuilder.ResponseBuilder;
import com.scando.learning.common.models.rest.Status;
import com.scando.learning.common.utils.WebUtils;
import com.scando.learning.modules.student.models.rest.*;
import com.scando.learning.modules.student.service.StudentService;
import com.scando.learning.modules.student.validator.StudentValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@Slf4j
@RestController
@Api(tags = {SwaggerHeads.STUDENT_API})
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    ResponseBuilder responseBuilder;

    @Autowired
    StudentValidator studentValidator;


    @PostMapping(ApiUrls.STUDENT_ENROLL_CLASS)
    @ApiOperation(value = "Student Enroll Class")
    public ResponseEntity<EnrollClassResponse> studentEnroll(@RequestBody @Valid EnrollClassRequest enrollClassRequest, BindingResult bindingResult) {
        try {
            Status status = WebUtils.getStatus();
            status.setApiId(17);
            WebUtils.setStatus(status);
            if (bindingResult.hasErrors()) {
                LOGGER.error("Input validation failed");
                throw new ControllerException(bindingResult, ErrorCodeEnum.STUDENT_ENROLL_CLASS_VALIDATION_FAILED);
            }

            EnrollClassResponse apiResponse = studentService.enrollClass(enrollClassRequest);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            LOGGER.error(Arrays.toString(e.getStackTrace()));
            throw e;
        }
    }


    @GetMapping(ApiUrls.CHECK_LIVE_CLASS)
    @ApiOperation(value = "Check the class is live or Not")
    public ResponseEntity<CheckLiveClassResponse> liveClassCheck(@PathVariable("id") Long classId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //need to handle validation errors
        }

        CheckLiveClassResponse apiResponse = studentService.liveClassCheck(classId);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(ApiUrls.CREATE_STUDENT)
    @ApiOperation(value = "Student Create/Update")
    public ResponseEntity<StudentSignUpResponse> studentSignUp(
            @RequestBody @Valid StudentSignUpRequest studentSignUpRequest, BindingResult bindingResult) {
        Status status = WebUtils.getStatus();
        status.setApiId(6);
        WebUtils.setStatus(status);
        if (bindingResult.hasErrors()) {
            LOGGER.debug("Input validation failed for {}", StudentSignUpRequest.class);
            status.setEndTime(System.currentTimeMillis());
            throw new ControllerException(
                    bindingResult,
                    ErrorCodeEnum.STUDENT_SIGNUP_INPUT_VALIDATION_FAILED
            );
        }

        try {
            StudentSignUpResponse apiResponse = studentService.studentSignUp(studentSignUpRequest);
            return ResponseEntity.ok(apiResponse);
        } catch (ServiceException ex) {
            throw new ServiceException(ex.getErrorCode());
        }
    }

    @GetMapping(ApiUrls.GET_STUDENT_CLASS_DETAIL_SPECIFIC_CLASS)
    @ApiOperation(value = "get class details of specific class")
    public ResponseEntity<GetStudentClassDetailResponse> getClassDetail(@RequestParam("classId") Long classId) {
        try {
            Status status = new Status();
            status.setApiId(6);
            status.setStartTime(System.currentTimeMillis());
            studentValidator.validateGetClassDetailsOfSpecificClassRequest(classId, status);
            GetStudentClassDetailResponse apiResponse = studentService.getClassDetail(classId);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception exception) {
            throw exception;
        }
    }

    /*@GetMapping(ApiUrls.GET_ENROLLED_CLASSES)
    @ApiOperation(value = "get enrolled classes")
    public ResponseEntity<GetEnrolledClassListResponse> getEnrolledClass(@PathVariable("id") Long userId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //need to handle validation errors
        }

        GetEnrolledClassListResponse apiResponse = studentService.getEnrolledClass(userId);
        return ResponseEntity.ok(apiResponse);
    }*/

    @PostMapping(ApiUrls.UN_ENROLL_CLASS)
    @ApiOperation(value = "Student Un Enroll Class")
    public ResponseEntity<UnEnrollResponse> studentUnEnroll(@RequestBody @Valid EnrollClassRequest enrollClassRequest, BindingResult bindingResult) {
        try {
            Status status = WebUtils.getStatus();
            status.setApiId(17);
            WebUtils.setStatus(status);
            if (bindingResult.hasErrors()) {
                LOGGER.error("Input validation failed");
                throw new ControllerException(bindingResult, ErrorCodeEnum.STUDENT_ENROLL_CLASS_VALIDATION_FAILED);
            }

            UnEnrollResponse apiResponse = studentService.unEnrollClass(enrollClassRequest);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            LOGGER.error(Arrays.toString(e.getStackTrace()));
            throw e;
        }
    }

    @GetMapping(ApiUrls.GET_ENROLLED_TIMETABLE)
    @ApiOperation(value = "Get Enrolled class Timetable")
    public ResponseEntity<GetTimetableResponse>
    getEnrolledClassTimeTable(@PathVariable("classId") Long classRoomId) {
        Status status = WebUtils.getStatus();
        status.setApiId(19);
        WebUtils.setStatus(status);
        if (classRoomId > 999999999 || classRoomId < 100000000) {
            LOGGER.debug("Input validation failed for Get timeTable");
            throw new ControllerException(
                    null,
                    ErrorCodeEnum.ENROLLED_CLASS_GET_TIMETABLE_INPUTVALIDATION_FAILED
            );
        }

        GetTimetableResponse apiResponse = studentService.getTimeTable(classRoomId);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(ApiUrls.GET_STUDENT_CLASSES_ON_SPECIFIC_DAY)
    @ApiOperation(value = "get student classes on specific day")
    public ResponseEntity<GetStudentClassesResponse> getOwnClass(@RequestParam("userId") Long userId, @RequestParam("day") String day) {
        try {
            Status status = new Status();
            status.setApiId(3);
            studentValidator.validateGetStudentOwnClassRequest(userId, day, status);
            GetStudentClassesResponse apiResponse = studentService.getStudentClassOnSpecificDay(userId, day);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @GetMapping(ApiUrls.GET_STUDENT_ALL_CLASS_DETAILS)
    @ApiOperation(value = "get details of all class of a student")
    public ResponseEntity<GetStudentClassDetailResponse> getAllClassDetails(@RequestParam("userId") Long userId,
                                                                            @RequestParam(value = "isScheduled", required = false) Long isScheduled) {
        try {
            Status status = new Status();
            status.setApiId(6);
            status.setStartTime(System.currentTimeMillis());
            studentValidator.validateGetAllClassDetailsRequest(userId);
            GetStudentClassDetailResponse apiResponse = studentService.getAllClassDetail(userId, isScheduled);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @GetMapping(ApiUrls.GET_STUDENT_ENROLL_LIST)
    @ApiOperation(value = "get list of enrolled classes and waiting classes")
    public ResponseEntity<GetEnrollListResponse> getEnrolList(@ModelAttribute @Valid GetEnrollListRequest enrollListRequest, BindingResult bindingResult) {
        try {
            Status status = WebUtils.getStatus();
            status.setApiId(27);
            WebUtils.setStatus(status);
            if (bindingResult.hasErrors()) {
                LOGGER.error("Input validation failed");
                throw new ControllerException(bindingResult, ErrorCodeEnum.GET_STUDENT_CLASS_ENROLL_LIST_VALIDATION_FAILED);
            }

            if (enrollListRequest.getEnrollStatus()!=null) {
                studentValidator.validateEnrollListRequestStatus(enrollListRequest.getEnrollStatus());
            }

            GetEnrollListResponse apiResponse = studentService.getEnrollList(enrollListRequest);
            return ResponseEntity.ok(apiResponse);
        } catch (ServiceException e) {
            throw new ServiceException(e.getErrorCode());
        }
    }
}
