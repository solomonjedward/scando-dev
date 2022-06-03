package com.scando.learning.common.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum ErrorCodeEnum {

    SUCCESS(HttpStatus.OK, 20001, "Success"),
    ERROR(HttpStatus.SERVICE_UNAVAILABLE, 20002, "Error"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 40000, "Bad request"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 40001, "Unauthorized access"),
    INVALID_HEADER(HttpStatus.UNAUTHORIZED, 40002, "Invalid auth header"),
    FORBIDDEN(HttpStatus.FORBIDDEN, 40003, "Permission denied"),
    INPUT_VALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, 40006, "Input validation failed"),
    DATA_NOT_FOUND(HttpStatus.BAD_REQUEST, 200003, "Data not found"),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, 40004,
            "Unable to process request at this time"),

    INVALID_FORMAT(HttpStatus.BAD_REQUEST, 40005,
            "Cannot deserialize value"),
    INPUT_FIELDS_EMPTY(HttpStatus.BAD_REQUEST, 40007,
            "Required properties of the request body are either missing or empty."),
    MAX_PROFILE_UPLOAD_SIZE(HttpStatus.BAD_REQUEST, 40008, "File size cannot exceed 250kb."),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 50000, "Internal Server Error"),

    //App registration validation
    APP_REGISTRATION_VALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, 200101,
            "Input validation failed"),
    APP_NOT_REGISTERED(HttpStatus.FORBIDDEN, 200102, "App not registered"),

    //Check Request Error Codes
    CHECK_REQUEST_INPUT_VALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, 200201,
            "Input validation failed"),
    CHECK_REQUEST_INVALID_APP_ID(HttpStatus.FORBIDDEN, 200202,
            "App not registered"),

    //Generate OTP Error Codes
    GENERATE_OTP_INPUT_VALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, 200301,
            "Input validation failed"),
    GENERATE_OTP_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 200302,
            "Failed to generate otp"),

    //Profile Upload
    PROFILE_UPLOAD_INPUT_FIELDS_EMPTY(HttpStatus.BAD_REQUEST, 200401,
            "Required properties of the request body are either missing or empty."),
    PROFILE_UPLOAD_MAX_PROFILE_UPLOAD_SIZE(HttpStatus.BAD_REQUEST, 200402,
            "File size cannot exceed 250kb."),

    //Verify Otp
    VERIY_OTP_INPUT_VALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, 200501,
            "Input validation failed"),
    VERIY_OTP_INVALID_APP_ID(HttpStatus.UNPROCESSABLE_ENTITY, 200502,
            "App not registered"),
    VERIY_OTP_INVALID_OTP(HttpStatus.UNPROCESSABLE_ENTITY, 200503,
            "Invalid otp"),
    VERIY_OTP_EXPIRED(HttpStatus.UNPROCESSABLE_ENTITY, 200504,
            "Expired otp"),
    VERIY_OTP_USED(HttpStatus.UNPROCESSABLE_ENTITY, 200505,
            "Used otp"),

    //CREATE EDIT CLASSROOM VALIDATION
    CREATE_CLASSROOM_VALIDATION(HttpStatus.UNPROCESSABLE_ENTITY, 200601,
            "Input validation failed"),
    INVALID_DAYS_GIVEN(HttpStatus.UNPROCESSABLE_ENTITY, 200602,
            "Invalid day given, only ['01', '02', '03', '04', '05', '06', '07'] accepted"),
    INVALID_CLASS_TYPE(HttpStatus.UNPROCESSABLE_ENTITY, 200603,
            "Invalid classType given, only [1,2,3,4] acceptable"),
    CLASSROOM_NOT_FOUND(HttpStatus.NOT_FOUND, 200604,
            "ClassRoom not found"),
    CLASSROOM_FORBIDDEN_ACCESS(HttpStatus.UNAUTHORIZED, 200605,
            "User doesn't have permission to edit class"),

    //Student Signup
    STUDENT_SIGNUP_INPUT_VALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, 200701,
            "Input validation failed"),
    STUDENT_SIGNUP_NUMBER_NOT_REGISTERED(HttpStatus.UNPROCESSABLE_ENTITY, 200702,
            "Number not registered with scando"),
    STUDENT_SIGNUP_EXISTING_NUMBER(HttpStatus.UNPROCESSABLE_ENTITY, 200703,
            "Number already exists"),
    STUDENT_SIGNUP_NUMBER_NOT_VERIFIED(HttpStatus.UNPROCESSABLE_ENTITY, 200704,
            "Number not verified"),
    STUDENT_SIGNUP_VERIFIED_OTP_EXPIRED(HttpStatus.UNPROCESSABLE_ENTITY, 200705,
            "Verified otp expired"),
    STUDENT_SIGNUP_INVALID_APP_ID(HttpStatus.FORBIDDEN, 200706,
            "App not registered"),


    //DELETE CLASSROOM
    DELETE_CLASS_VALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, 201001,
            "Input validation failed"),
    DELETE_CLASS_NOT_FOUND(HttpStatus.NOT_FOUND, 201002,
            "Class not found"),
    DELETE_CLASS_FORBIDDEN_ACCESS(HttpStatus.UNAUTHORIZED, 201003,
            "User doesn't have permission to delete class"),

    //CREATE TIME TABLE
    CREATE_TIME_TABLE_VALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, 201101,
            "Input validation failed"),
    CREATE_TIME_TABLE_CLASSID_NOT_MATCHING(HttpStatus.UNPROCESSABLE_ENTITY, 201102,
            "ClassId and teacherId are not matching"),

    CREATE_TIME_TABLE_CLASSID_NOT_FOUND(HttpStatus.UNPROCESSABLE_ENTITY, 201103,
            "Classroom not exist"),

    //GET CLASS ENROLL REQUEST LIST
    GET_ENROLL_REQUEST_LIST_VALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, 201401,
            "Input validation failed"),
    GET_ENROLL_REQUEST_LIST_ENROLL_STATUS_VALIDATION(HttpStatus.UNPROCESSABLE_ENTITY, 201402,
            "Invalid enrollStatus given 1:enrolled list, 0: unEnrolled list"),

    //APPROVE ENROLL REQUEST
    APPROVE_REQUEST_VALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, 201501,
            "Input validation failed"),
    APPROVE_REQUEST_NOT_FOUND(HttpStatus.UNPROCESSABLE_ENTITY, 201502,
            "Enroll request not found"),
    APPROVE_CLASS_FORBIDDEN(HttpStatus.UNAUTHORIZED, 201503,
            "User doesn't have permission to approve class"),

    //STUDENT ENROLL CLASS
    STUDENT_ENROLL_CLASS_VALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, 201701,
            "Input validation failed"),
    STUDENT_ENROLL_CLASS_ENROLL_TYPE_ERROR(HttpStatus.UNPROCESSABLE_ENTITY, 201702,
            "invalid enrollType given, 1:enroll class and 2: unEnroll class"),
    STUDENT_ENROLL_CLASS_ALREADY_ENROLLED(HttpStatus.FORBIDDEN, 201703, "" +
            "already enrolled to the specific classRoom"),
    STUDENT_UN_ENROLL_CLASS_NOT_ENROLLED(HttpStatus.FORBIDDEN, 201704,
            "Requested class is not enrolled, please enroll class to unEnroll"),

    //Profile Upload
    FILE_NOT_FOUND(HttpStatus.FORBIDDEN, 201705,
            "File not Found "),

    //Create Teacher
    CREATE_TEACHER_INPUT_VALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, 201706,
            "Input validation failed"),
    INVALID_USER_TYPE(HttpStatus.FORBIDDEN, 201707,
            "Invalid userType for Teacher "),
    INVALID_SUBJECT_CODE(HttpStatus.FORBIDDEN, 201708,
            "Invalid subject Code for Teacher "),
    PHONE_NUMBER_ALREADY_EXIST(HttpStatus.FORBIDDEN, 201709,
            "Phone number Already Exists "),
    PHONE_NUMBER_NOT_VERIFIED(HttpStatus.FORBIDDEN, 201710,
            "Phone number not verified "),

    GET_TIMETABLE_INPUTVALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, 201801,
            "Input validation failed"),

    //enrolled class timetable
    ENROLLED_CLASS_GET_TIMETABLE_INPUTVALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, 201901,
            "Input validation failed"),

    ENROLLED_CLASS_GET_TIMETABLE_NOT_ENROLLED(HttpStatus.UNPROCESSABLE_ENTITY, 201902,
            "Class not enrolled"),

    ENROLLED_CLASS_GET_TIMETABLE_ENROLL_NOT_APPROVED(HttpStatus.UNPROCESSABLE_ENTITY, 201903,
            "Enroll request not approved"),

    //unenroll student
    TEACHER_UN_ENROLL_VALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, 251501,
            "Input validation failed"),
    TEACHER_UN_ENROLL_ENROLL_TYPE_ERROR(HttpStatus.UNPROCESSABLE_ENTITY, 251702,
            "invalid enrollType given, 2: unEnroll class"),

    USER_TOKEN_MISMATCH(HttpStatus.UNPROCESSABLE_ENTITY, 251703,
            "UserId and userToken doesn't match"),

    INVALID_SCHEDULED_TYPE(HttpStatus.UNPROCESSABLE_ENTITY, 251704,
            "Invalid isScheduled value "),
    GET_STUDENT_CLASS_ENROLL_LIST_VALIDATION_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, 201401,
            "Input validation failed"),
    GET_STUDENT_ENROLL_LIST_ENROLL_STATUS_VALIDATION(HttpStatus.UNPROCESSABLE_ENTITY, 201402,
            "Invalid enrollStatus given 1:enrolled list, 0: unEnrolled list");

    private final HttpStatus status;

    private final Integer code;

    private final String description;

    private final boolean hidden;

    ErrorCodeEnum(HttpStatus status, Integer code, String description) {
        this.status = status;
        this.code = code;
        this.description = description;
        this.hidden = false;
    }

    ErrorCodeEnum(HttpStatus status, Integer code, String description, boolean hidden) {
        this.status = status;
        this.code = code;
        this.description = description;
        this.hidden = hidden;
    }

    public static ErrorCodeEnum[] getVisibleErrorCodes() {
        List<ErrorCodeEnum> errorCodeList = new ArrayList<>();
        for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            if (!errorCodeEnum.isHidden()) {
                errorCodeList.add(errorCodeEnum);
            }
        }
        ErrorCodeEnum[] errorCodeArray = new ErrorCodeEnum[errorCodeList.size()];
        return errorCodeList.toArray(errorCodeArray);
    }
}
