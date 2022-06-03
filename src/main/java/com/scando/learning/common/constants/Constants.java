package com.scando.learning.common.constants;

import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor
public final class Constants {

    public static final String BAD_REQUEST = "application/json";

    public static final String MEDIA_TYPE = "application/json";

    public static final String METHOD_NOT_ALLOWED = "Method not allowed";

    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    public static final String SERVICE_UNAVAILABLE = "Service Unavailable";

    public static final String APPROVE_MESSAGE = "Joining request approved for class ";

    public  static  final  String APPROVE_TITLE = "Approved";

    public static final String ENROLL_MESSAGE = " requested to join";

    public static final String ENROLL_TITLE = "New joining request";

    public static final String PROFILE_S3_BUCKET = "scando-profile";

    public static final String PROFILE_UPLOAD_SUCCESS = "Profile upload Success";
    public static final String MESSAGE_SUCCESS = "success";
    public static final int STATUS_CODE_200 = 200;
    public static final String NO_DATA_FOUND = "No data Found";

    public static HashMap<Long, String> subject = new HashMap();
    static {
        subject.put(7000L, "Mathematics");
        subject.put(7001L, "English");
        subject.put(7002L, "Physics");
        subject.put(7003L, "Chemistry");
        subject.put(7004L, "ComputerScience");
    }

    public static final String GET_TIMETABLE_RESPONSE = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 3\n" +
            "\t},\n" +
            "\t\"Data\": {\n" +
            "\t\t\"classId\": \"16\",\n" +
            "\t\t\"timeTableId\": 12,\n" +
            "\t\t\"days\": [1, 2, 3, 4, 5, 6, 7],\n" +
            "\t\t\"timeData\": [{\n" +
            "\t\t\t\t\"1\": {\n" +
            "\t\t\t\t\t\"startTime\": 1612121212,\n" +
            "\t\t\t\t\t\"endTime\": 1612121212\n" +
            "\t\t\t\t}\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"2\": {\n" +
            "\t\t\t\t\t\"startTime\": 1612121212,\n" +
            "\t\t\t\t\t\"endTime\": 1612121212\n" +
            "\t\t\t\t}\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"3\": {\n" +
            "\t\t\t\t\t\"startTime\": 1612121212,\n" +
            "\t\t\t\t\t\"endTime\": 1612121212\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t],\n" +
            "\t\t\"repeatEnabled\": true\n" +
            "\t}\n" +
            "}";

    public static final String GET_APPROVED_STUDENT_LIST_RESPONSE = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 3\n" +
            "\t},\n" +
            "\t\"Data\": {\n" +
            "\t\t\"pageDetails\":{\n" +
            "\t\t\t\"page\":1,\n" +
            "\t\t\t\"pageCount\":12,\n" +
            "\t\t\t\"pageSize\":12,\n" +
            "\t\t\t\"totalElements\":100\n" +
            "\t\t},\n" +
            "\t\t\"list\":[\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"studentId\":12,\n" +
            "\t\t\t\t\"studentName\":\"Name\"\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"studentId\":13,\n" +
            "\t\t\t\t\"studentName\":\"Name2\"\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t}\n" +
            "}";

    public static final String APPROVE_STUDENT_RESPONSE = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 4\n" +
            "\t},\n" +
            "\t\"Data\": {\n" +
            "                \"statusCode\": 20001,\n" +
            "                \"statusMessage\":\"Approved successfully\"\n" +
            "\t}\n" +
            "}";

    public static final String CREATE_CLASSROOM_RESPONSE = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 6\n" +
            "\t},\n" +
            "\t\"Data\": {\n" +
            "\t\t\"classRoomId\": 6444434\n" +
            "\n" +
            "\t}\n" +
            "}";

    public static final String CREATE_TIMETABLE_RESPONSE = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 6\n" +
            "\t},\n" +
            "\t\"Data\": {\n" +
            "\t\t\"timeTableId\": 6444434\n" +
            "\n" +
            "\t}\n" +
            "}";

    public static final String DELETE_CLASSROOM_RESPONSE = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 6\n" +
            "\t},\n" +
            "\t\"Data\": {\n" +
            "\t\t\"statusCode\":20001,\n" +
            "\t\t\"statusMessage\":\"Classroom deleted successfully\"\n" +
            "\n" +
            "\t}\n" +
            "}";

    public static final String GET_CLASSROOM_RESPONSE = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 6\n" +
            "\t},\n" +
            "\t\"Data\": {\n" +
            "\t\t\"classId\": 6444434,\n" +
            "\t\t\"className\": \"10 th class Maths\",\n" +
            "\t\t\"timetableEnabled\": false,\n" +
            "\t\t\"enrolledStudents\": 15,\n" +
            "\t\t\"classRecordings\": 0,\n" +
            "\t\t\"studyMaterials\": {\n" +
            "\t\t\t\"document\": 0,\n" +
            "\t\t\t\"voice\": 0,\n" +
            "\t\t\t\"video\": 0\n" +
            "\t\t}\n" +
            "\n" +
            "\t}\n" +
            "}";

    public static final String GET_SCHEDULED_CLASS_RESPONSE = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 6\n" +
            "\t},\n" +
            "\t\"Data\": {\n" +
            "\t\t\"pageDetails\": {\n" +
            "\t\t\t\"page\": 1,\n" +
            "\t\t\t\"pageCount\": 10,\n" +
            "\t\t\t\"pageSize\": 20,\n" +
            "\t\t\t\"totalElements\": 100\n" +
            "\t\t},\n" +
            "\t\t\"list\": [{\n" +
            "\t\t\t\"classType\": 1,\n" +
            "\t\t\t\"classId\": 1,\n" +
            "\t\t\t\"className\": \"10 th Maths\",\n" +
            "\t\t\t\"days\": [1, 2, 3, 4, 5, 6, 7],\n" +
            "\t\t\t\"startTime\": 1612121212,\n" +
            "\t\t\t\"endTime\": 1612121212,\n" +
            "\t\t\t\"Activity\": {\n" +
            "\t\t\t\t\"doutSession\": 1,\n" +
            "\t\t\t\t\"scheduledTest\": 1,\n" +
            "\t\t\t\t\"homeWork\": 1,\n" +
            "\t\t\t\t\"notice\": 2\n" +
            "\t\t\t},\n" +
            "\t\t\t\"studyMaterials\": {\n" +
            "\t\t\t\t\"document\": 0,\n" +
            "\t\t\t\t\"voice\": 0,\n" +
            "\t\t\t\t\"video\": 0\n" +
            "\t\t\t}\n" +
            "\t\t}, {\n" +
            "\t\t\t\"classType\": 2,\n" +
            "\t\t\t\"classId\": 2,\n" +
            "\t\t\t\"className\": \"9 th Maths\",\n" +
            "\t\t\t\"days\": [1, 2, 3, 4, 5, 6, 7],\n" +
            "\t\t\t\"Activity\": {\n" +
            "\t\t\t\t\"doutSession\": 1,\n" +
            "\t\t\t\t\"scheduledTest\": 1,\n" +
            "\t\t\t\t\"homeWork\": 1,\n" +
            "\t\t\t\t\"notice\": 2\n" +
            "\t\t\t},\n" +
            "\t\t\t\"studyMaterials\": {\n" +
            "\t\t\t\t\"document\": 0,\n" +
            "\t\t\t\t\"voice\": 0,\n" +
            "\t\t\t\t\"video\": 0\n" +
            "\t\t\t}\n" +
            "\t\t}]\n" +
            "\n" +
            "\t}\n" +
            "}";

    public static final String TEACHER_SIGNUP_RESPONSE = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 3\n" +
            "\t},\n" +
            "\t\"Data\": {\n" +
            "\t\t\"statusCode\":20001,\n" +
            "\t\t\"statusMessage\" :\"created successfully\",\n" +
            "\t\t\"teacherId\": 101\n" +
            "\t}\n" +
            "}";

    public static final String UN_ENROLL_STUDENT_RESPONSE = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 3\n" +
            "\t},\n" +
            "\t\"Data\": {\n" +
            "\t\t\"statusCode\":20001,\n" +
            "\t\t\"statusMessage\" :\"Student Unerollment successfull\"\n" +
            "\t}\n" +
            "}";

    public static final String CHECK_ACCOUNT_SUCCESS = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 1\n" +
            "\t},\n" +
            "\t\"Data\":{\n" +
            "\t\t\"status\":\"exits\",\n" +
            "\t\t\"code\":\"1\"\n" +
            "\t}\n" +
            "}";

    public static final String CHECK_ACCOUNT_FAILED = "{\n" +
            "\n" +
            "\t\"Status\":{\n" +
            "\t\t\"statusCode\":20002,\n" +
            "\t\t\"statusMessage\":\"Error\",\n" +
            "\t\t\"startTime\":1610101010,\n" +
            "\t\t\"endTime\":1610101010,\n" +
            "\t\t\"apiId\":1\n" +
            "\t},\n" +
            "\t\"Data\":{\n" +
            "\t\t\"status\":\"Not exits\",\n" +
            "\t\t\"code\":\"0\"\n" +
            "\t}\n" +
            "}";

    public static final String GENERATE_OTP_SUCCESS = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 2\n" +
            "\t},\n" +
            "\t\"Data\":{\n" +
            "\t\t\"status\":\"Successfully generated\",\n" +
            "\t\t\"code\":\"1\"\n" +
            "\t}\n" +
            "}";

    public static final String GENERATE_OTP_FAILED = "{\n" +
            "\n" +
            "\t\"Status\":{\n" +
            "\t\t\"statusCode\":20002,\n" +
            "\t\t\"statusMessage\":\"Error\",\n" +
            "\t\t\"startTime\":1610101010,\n" +
            "\t\t\"endTime\":1610101010,\n" +
            "\t\t\"apiId\":2\n" +
            "\t},\n" +
            "\t\"Data\":{\n" +
            "\t\t\"code\":202201,\n" +
            "\t\t\"status\":\"Failed to send otp\"\n" +
            "\t}\n" +
            "}";


    public static final String VERIFY_OTP_SUCCESS = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 3\n" +
            "\t},\n" +
            "\t\"Data\": {\n" +
            "\t\t\"code\":20001,\n" +
            "\t\t\"staus\": \" OTP verified\"\n" +
            "\t}\n" +
            "}";
    public static final String VERIFY_OTP_FAILED = "{\n" +
            "\n" +
            "\t\"Status\":{\n" +
            "\t\t\"statusCode\":20002,\n" +
            "\t\t\"statusMessage\":\"Error\",\n" +
            "\t\t\"startTime\":1610101010,\n" +
            "\t\t\"endTime\":1610101010,\n" +
            "\t\t\"apiId\":3\n" +
            "\t},\n" +
            "\t\"Data\":{\n" +
            "\t\t\"code\":202201,\n" +
            "\t\t\"status\":\"Failed to verify the user\"\n" +
            "\t}\n" +
            "}";

    public static final String PROFILE_PIC_UPLOAD_SUCCESS = "\n" +
            "\t\"Status\":{\n" +
            "\t\t\"statusCode\":20001,\n" +
            "\t\t\"statusMessage\":\"Success\",\n" +
            "\t\t\"startTime\":1610101010,\n" +
            "\t\t\"endTime\":1610101010,\n" +
            "\t\t\"apiId\":3\n" +
            "\t},\n" +
            "\t\"Data\":{\n" +
            "\t\t\"url\":\"aws link\"\n" +
            "\t}";

    public static final String PROFILE_PIC_UPLOAD_FAILED = "\n" +
            "\t\"Status\":{\n" +
            "\t\t\"statusCode\":20002,\n" +
            "\t\t\"statusMessage\":\"Error\",\n" +
            "\t\t\"startTime\":1610101010,\n" +
            "\t\t\"endTime\":1610101010,\n" +
            "\t\t\"apiId\":3\n" +
            "\t},\n" +
            "\t\"Data\":{\n" +
            "\t\t\"code\":202201,\n" +
            "\t\t\"status\":\"Failed to upload\"\n" +
            "\t}";

    public static final String CHECK_LIVE_CLASS_SUCCESS = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 3\n" +
            "\t},\n" +
            "\t\"Data\": {\n" +
            "\t\t\"live\": true\n" +
            "\t}\n" +
            "}";

    public static final String ENROLL_CLASS_SUCCESS = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 3\n" +
            "\t},\n" +
            "\t\"Data\": {\n" +
            "\t\t\"id\": 123456789\n" +
            "\t}\n" +
            "}";

    public static final String ENROLL_CLASS_FAILED = "{\n" +
            "\n" +
            "\t\"Status\":{\n" +
            "\t\t\"statusCode\":20002,\n" +
            "\t\t\"statusMessage\":\"Error\",\n" +
            "\t\t\"startTime\":1610101010,\n" +
            "\t\t\"endTime\":1610101010,\n" +
            "\t\t\"apiId\":3\n" +
            "\t},\n" +
            "\t\"Data\":{\n" +
            "\t\t\"code\":202201,\n" +
            "\t\t\"status\":\"Failed to enroll in the class \"\n" +
            "\t}\n" +
            "}";

    public static final String SCHEDULED_CLASS = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 7\n" +
            "\t},\n" +
            "\t\"Data\": [{\n" +
            "\t        \"classType\" : 1,\n" +
            "\t\t\"classId\": 1,\n" +
            "\t\t\"className\": \"10 th Maths\",\n" +
            "\t\t\"days\": [1, 2, 3, 4, 5, 6, 7],\n" +
            "\t\t\"startTime\": 1612121212,\n" +
            "\t\t\"endTime\": 1612121212,\n" +
            "\t\t\"Activity\": {\n" +
            "\t\t     \"doutSession\": 1,\n" +
            "\t\t     \"scheduledTest\":1,\n" +
            "\t\t     \"homeWork\":1,\n" +
            "\t\t     \"notice\":2     \n" +
            "\t\t},\n" +
            "\t\t\"studyMaterials\": {\n" +
            "\t\t\t\"document\": 0,\n" +
            "\t\t\t\"voice\": 0,\n" +
            "\t\t\t\"video\": 0\n" +
            "\t\t}\n" +
            "\t}, {\n" +
            "\t\t\"classType\" : 2,\n" +
            "\t\t\"classId\": 2,\n" +
            "\t\t\"className\": \"9 th Maths\",\n" +
            "\t\t\"days\": [1, 2, 3, 4, 5, 6, 7],\n" +
            "\t\t\"Activity\": {\n" +
            "\t\t     \"doutSession\": 1,\n" +
            "\t\t     \"scheduledTest\":1,\n" +
            "\t\t     \"homeWork\":1,\n" +
            "\t\t     \"notice\":2     \n" +
            "\t\t},\n" +
            "\t\t\"studyMaterials\": {\n" +
            "\t\t\t\"document\": 0,\n" +
            "\t\t\t\"voice\": 0,\n" +
            "\t\t\t\"video\": 0\n" +
            "\t\t}\n" +
            "\t}]\n" +
            "\n" +
            "}";

    public static final String STUDENT_SIGNUP_SUCCESS = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 3\n" +
            "\t},\n" +
            "\t\"Data\": {\n" +
            "\t\t\"id\": 101\n" +
            "\t}\n" +
            "}";

    public static final String STUDENT_SIGNUP_FAILED = "{\n" +
            "\n" +
            "\t\"Status\":{\n" +
            "\t\t\"statusCode\":20002,\n" +
            "\t\t\"statusMessage\":\"Error\",\n" +
            "\t\t\"startTime\":1610101010,\n" +
            "\t\t\"endTime\":1610101010,\n" +
            "\t\t\"apiId\":3\n" +
            "\t},\n" +
            "\t\"Data\":{\n" +
            "\t\t\"code\":202201,\n" +
            "\t\t\"status\":\"Failed to create user \"\n" +
            "\t}\n" +
            "}";
    public static final String STUDENT_ENROLL_SUCCESS = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 3\n" +
            "\t},\n" +
            "\t\"Data\": {\n" +
            "\t\t\"id\": 123456789\n" +
            "\t}\n" +
            "}";

    public static final String STUDENT_ENROLL_FAILED = "{\n" +
            "\n" +
            "\t\"Status\":{\n" +
            "\t\t\"statusCode\":20002,\n" +
            "\t\t\"statusMessage\":\"Error\",\n" +
            "\t\t\"startTime\":1610101010,\n" +
            "\t\t\"endTime\":1610101010,\n" +
            "\t\t\"apiId\":3\n" +
            "\t},\n" +
            "\t\"Data\":{\n" +
            "\t\t\"code\":202201,\n" +
            "\t\t\"status\":\"Failed to unEnroll from the class\"\n" +
            "\t}\n" +
            "}\n";

    public static final String GET_STUDENT_TIMETABLE_RESPONSE = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 3\n" +
            "\t},\n" +
            "\t\"Data\": {\n" +
            "\t\t\"classId\": \"16\",\n" +
            "\t\t\"timeTableId\": 12,\n" +
            "\t\t\"days\": [1, 2, 3, 4, 5, 6, 7],\n" +
            "\t\t\"timeData\": [{\n" +
            "\t\t\t\t\"1\": {\n" +
            "\t\t\t\t\t\"startTime\": 1612121212,\n" +
            "\t\t\t\t\t\"endTime\": 1612121212\n" +
            "\t\t\t\t}\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"2\": {\n" +
            "\t\t\t\t\t\"startTime\": 1612121212,\n" +
            "\t\t\t\t\t\"endTime\": 1612121212\n" +
            "\t\t\t\t}\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"3\": {\n" +
            "\t\t\t\t\t\"startTime\": 1612121212,\n" +
            "\t\t\t\t\t\"endTime\": 1612121212\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t],\n" +
            "\t\t\"repeatEnabled\": true\n" +
            "\t}\n" +
            "}";


    public static final String GET_STUDENT_ENROLLED_CLASS_RESPONSE = "{\n" +
            "\n" +
            "\t\"Status\": {\n" +
            "\t\t\"statusCode\": 20001,\n" +
            "\t\t\"statusMessage\": \"Success\",\n" +
            "\t\t\"startTime\": 1610101010,\n" +
            "\t\t\"endTime\": 1610101010,\n" +
            "\t\t\"apiId\": 6\n" +
            "\t},\n" +
            "\t\"Data\": {\n" +
            "\t\t\"pageDetails\": {\n" +
            "\t\t\t\"page\": 1,\n" +
            "\t\t\t\"pageCount\": 10,\n" +
            "\t\t\t\"pageSize\": 20,\n" +
            "\t\t\t\"totalElements\": 100\n" +
            "\t\t},\n" +
            "\t\t\"list\": [{\n" +
            "\t\t\t\"classType\": 1,\n" +
            "\t\t\t\"classId\": 1,\n" +
            "\t\t\t\"className\": \"10 th Maths\",\n" +
            "\t\t\t\"days\": [1, 2, 3, 4, 5, 6, 7],\n" +
            "\t\t\t\"startTime\": 1612121212,\n" +
            "\t\t\t\"endTime\": 1612121212,\n" +
            "\t\t\t\"Activity\": {\n" +
            "\t\t\t\t\"doutSession\": 1,\n" +
            "\t\t\t\t\"scheduledTest\": 1,\n" +
            "\t\t\t\t\"homeWork\": 1,\n" +
            "\t\t\t\t\"notice\": 2\n" +
            "\t\t\t},\n" +
            "\t\t\t\"studyMaterials\": {\n" +
            "\t\t\t\t\"document\": 0,\n" +
            "\t\t\t\t\"voice\": 0,\n" +
            "\t\t\t\t\"video\": 0\n" +
            "\t\t\t}\n" +
            "\t\t}, {\n" +
            "\t\t\t\"classType\": 2,\n" +
            "\t\t\t\"classId\": 2,\n" +
            "\t\t\t\"className\": \"9 th Maths\",\n" +
            "\t\t\t\"days\": [1, 2, 3, 4, 5, 6, 7],\n" +
            "\t\t\t\"Activity\": {\n" +
            "\t\t\t\t\"doutSession\": 1,\n" +
            "\t\t\t\t\"scheduledTest\": 1,\n" +
            "\t\t\t\t\"homeWork\": 1,\n" +
            "\t\t\t\t\"notice\": 2\n" +
            "\t\t\t},\n" +
            "\t\t\t\"studyMaterials\": {\n" +
            "\t\t\t\t\"document\": 0,\n" +
            "\t\t\t\t\"voice\": 0,\n" +
            "\t\t\t\t\"video\": 0\n" +
            "\t\t\t}\n" +
            "\t\t}]\n" +
            "\n" +
            "\t}\n" +
            "}";
}
