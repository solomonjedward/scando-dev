package com.scando.learning.common.constants;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class ApiUrls {
    public static final String API_SPEC_LOGIN = "/api-spec-login";

    public static final String BASE_URL = "/api/";

    public static final String APP_REGISTRATION = BASE_URL + "app/registration";

    public static final String LOGOUT = BASE_URL + "auth/logout";

    public static final String USER_CHECK_ACCOUNT = BASE_URL + "auth/check-account";

    public static final String GENERATE_OTP = BASE_URL + "auth/generate-otp";

    public static final String CREATE_TEACHER = BASE_URL + "auth/teacher/signup";

    public static final String CREATE_CLASSROOM = BASE_URL + "teacher/create-classroom";

    public static final String EDIT_CLASSROOM = BASE_URL + "teacher/update-classroom/{classId}";

    public static final String DELETE_CLASSROOM = BASE_URL + "teacher/delete-classroom";

    public static final String CREATE_TIMETABLE = BASE_URL + "teacher/create-timetable";

    public static final String GET_TIMETABLE = BASE_URL + "get-timetable/{classId}";

    public static final String GET_CLASSROOM = BASE_URL + "teacher/get-classroom";

    public static final String CREATE_STUDENT = BASE_URL + "auth/student/signup";

    public static final String STUDENT_ENROLL_CLASS = BASE_URL + "student/enroll-class";

    public static final String GET_CLASSES = BASE_URL + "user/get-classes";

    public static final String CHECK_LIVE_CLASS = BASE_URL + "student/check-liveClass";

    public static final String APPROVE_CLASS = BASE_URL + "teacher/approve-class";

    public static final String VERIFY_OTP = BASE_URL + "auth/verify-otp";

    public static final String PROFILE_UPLOAD = BASE_URL + "auth/profile-upload";

    public static final String GET_STUDENT_CLASS_DETAIL_SPECIFIC_CLASS = BASE_URL + "student/class-details";

    public static final String GET_ENROLLED_CLASSES = BASE_URL + "student/enrolled-class/{classId}";

    public static final String UN_ENROLL_CLASS = BASE_URL + "student/Un-enroll";

    public static final String GET_ENROLLED_TIMETABLE = BASE_URL + "student/enrolled-classes-timetable/{classId}";

    public static final String GET_TEACHER_OWN_CLASSES_ON_SPECIFIC_DAY = BASE_URL + "teacher/class-specific-day";

    public static final String UN_ENROLL_STUDENT = BASE_URL + "teacher/Un-enroll";

    public static final String APPROVE_REQUEST = BASE_URL + "teacher/approve";

    public static final String GET_APPROVAL_LIST = BASE_URL + "teacher/approval-list";

    public static final String GET_TEACHER_ALL_CLASSES = BASE_URL + "teacher/all-class";

    public static final String GET_STUDENT_CLASSES_ON_SPECIFIC_DAY = BASE_URL + "student/own-class";

    public static final String GET_STUDENT_ALL_CLASS_DETAILS = BASE_URL + "student/all-class-details";

    public static final String GET_TEACHER_CLASS_DETAIL_SPECIFIC_CLASS = BASE_URL + "teacher/class-details";

    public static final String GET_TEACHER_ALL_CLASS_DETAILS = BASE_URL + "teacher/all-class-details";

    public static final String GET_STUDENT_ENROLL_LIST = BASE_URL + "student/enroll-list";

    public static final String PUBLISH_NOTIFICATION = BASE_URL + "notification/publish";

    public static final String SEND_NOTIFICATION = BASE_URL + "notification/send";
}
