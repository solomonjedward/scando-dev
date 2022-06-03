package com.scando.learning.modules.student.controller;

import com.scando.learning.LearningApplication;
import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.common.constants.StatusEnum;
import com.scando.learning.modules.student.dao.StudentDao;
import com.scando.learning.modules.student.models.rest.EnrollClassRequest;
import com.scando.learning.modules.student.models.rest.EnrollClassResponse;
import com.scando.learning.modules.student.service.StudentService;
import com.scando.learning.modules.teacher.dao.TeacherDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = LearningApplication.class)
public class StudentEnrollUnEnrollClassControllerTest extends AbstractStudentControllerTest{

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private StudentDao studentDao;

    @MockBean
    private TeacherDao teacherDao;

    @Test
    void studentEnrollClassTestWithValidData()  throws Exception {
        EnrollClassRequest enrollClassRequest = getClassEnrollRequest(1L, 1L, 1);

        Mockito.when(studentService.enrollClass(enrollClassRequest)).thenReturn(new EnrollClassResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(studentDao.getEnrolledClassByClassIdAndStudentId(any(), any())).thenReturn(getClassEnrollData());

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.STUDENT_ENROLL_CLASS)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(enrollClassRequest))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void studentEnrollClassTestWithInValidData()  throws Exception {
        EnrollClassRequest enrollClassRequest = getClassEnrollRequest(null, 1L, 1);

        Mockito.when(studentService.enrollClass(enrollClassRequest)).thenReturn(new EnrollClassResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(studentDao.getEnrolledClassByClassIdAndStudentId(any(), any())).thenReturn(getClassEnrollData());

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.STUDENT_ENROLL_CLASS)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(enrollClassRequest))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));
    }

    @Test
    void studentEnrollClassTestWithInValidType()  throws Exception {
        EnrollClassRequest enrollClassRequest = getClassEnrollRequest(1L, 1L, 0);

        Mockito.when(studentService.enrollClass(enrollClassRequest)).thenReturn(new EnrollClassResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(studentDao.getEnrolledClassByClassIdAndStudentId(any(), any())).thenReturn(getClassEnrollData());

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.STUDENT_ENROLL_CLASS)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(enrollClassRequest))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));
    }

    @Test
    void studentEnrollClassTestWithInValidJson()  throws Exception {
        EnrollClassRequest enrollClassRequest = getClassEnrollRequest(1L, 1L, 0);

        Mockito.when(studentService.enrollClass(enrollClassRequest)).thenReturn(new EnrollClassResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(studentDao.getEnrolledClassByClassIdAndStudentId(any(), any())).thenReturn(getClassEnrollData());

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.STUDENT_ENROLL_CLASS)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("getJson(enrollClassRequest)")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));
    }

    @Test
    void studentEnrollClassTestWithoutInput()  throws Exception {
        EnrollClassRequest enrollClassRequest = getClassEnrollRequest(null, null, null);

        Mockito.when(studentService.enrollClass(enrollClassRequest)).thenReturn(new EnrollClassResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(studentDao.getEnrolledClassByClassIdAndStudentId(any(), any())).thenReturn(getClassEnrollData());

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.STUDENT_ENROLL_CLASS)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));
    }

    @Test
    void studentUnEnrollClassTestWithValidData()  throws Exception {
        EnrollClassRequest enrollClassRequest = getClassEnrollRequest(1L, 1L, 2);

        Mockito.when(studentService.enrollClass(enrollClassRequest)).thenReturn(new EnrollClassResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(studentDao.getEnrolledClassByClassIdAndStudentId(any(), any())).thenReturn(getClassEnrollData());

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.STUDENT_ENROLL_CLASS)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(enrollClassRequest))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void studentUnEnrollClassTestWithInValidData()  throws Exception {
        EnrollClassRequest enrollClassRequest = getClassEnrollRequest(null, 1L, 1);

        Mockito.when(studentService.enrollClass(enrollClassRequest)).thenReturn(new EnrollClassResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(studentDao.getEnrolledClassByClassIdAndStudentId(any(), any())).thenReturn(getClassEnrollData());

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.STUDENT_ENROLL_CLASS)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(enrollClassRequest))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));
    }

    @Test
    void studentUnEnrollClassTestWithInValidType()  throws Exception {
        EnrollClassRequest enrollClassRequest = getClassEnrollRequest(1L, 1L, 0);

        Mockito.when(studentService.enrollClass(enrollClassRequest)).thenReturn(new EnrollClassResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(studentDao.getEnrolledClassByClassIdAndStudentId(any(), any())).thenReturn(getClassEnrollData());

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.STUDENT_ENROLL_CLASS)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(enrollClassRequest))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));
    }

    @Test
    void studentUnEnrollClassTestWithInValidJson()  throws Exception {
        EnrollClassRequest enrollClassRequest = getClassEnrollRequest(1L, 1L, 0);

        Mockito.when(studentService.enrollClass(enrollClassRequest)).thenReturn(new EnrollClassResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(studentDao.getEnrolledClassByClassIdAndStudentId(any(), any())).thenReturn(getClassEnrollData());

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.STUDENT_ENROLL_CLASS)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("getJson(enrollClassRequest)")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));
    }

    @Test
    void studentUnEnrollClassTestWithoutInput()  throws Exception {
        EnrollClassRequest enrollClassRequest = getClassEnrollRequest(null, null, null);

        Mockito.when(studentService.enrollClass(enrollClassRequest)).thenReturn(new EnrollClassResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(studentDao.getEnrolledClassByClassIdAndStudentId(any(), any())).thenReturn(getClassEnrollData());

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.STUDENT_ENROLL_CLASS)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()));
    }

    protected EnrollClassRequest getClassEnrollRequest(Long classId, Long studentId, Integer enrollType) {
        EnrollClassRequest enrollClassRequest = new EnrollClassRequest();
        enrollClassRequest.setClassId(classId);
        return enrollClassRequest;
    }
}
