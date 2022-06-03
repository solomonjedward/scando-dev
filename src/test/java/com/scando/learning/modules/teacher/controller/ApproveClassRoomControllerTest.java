package com.scando.learning.modules.teacher.controller;

import com.scando.learning.LearningApplication;
import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.modules.teacher.dao.TeacherDao;
import com.scando.learning.modules.teacher.models.rest.ApproveClassRequest;
import com.scando.learning.modules.teacher.models.rest.ApproveClassResponse;
import com.scando.learning.modules.teacher.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = LearningApplication.class)
public class ApproveClassRoomControllerTest  extends AbstractTeacherControllerTest{

    @MockBean
    private TeacherService teacherService;

    @MockBean
    private TeacherDao teacherDao;

    @Test
    void approveClassRoomTestWithValidData() throws Exception {
        ApproveClassRequest approveClassRequest = getApproveClassRequest(1L, 1L);

        Mockito.when(teacherService.approveClass(approveClassRequest)).thenReturn(new ApproveClassResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(teacherDao.getEnrollRequestByClassIdAndStudentId(any(), any())).thenReturn(getClassEnrollData());

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.APPROVE_REQUEST)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(approveClassRequest))).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void approveClassRoomTestWithInValidData() throws Exception {
        ApproveClassRequest approveClassRequest = getApproveClassRequest(null, null);

        Mockito.when(teacherService.approveClass(approveClassRequest)).thenReturn(new ApproveClassResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(teacherDao.getEnrollRequestByClassIdAndStudentId(any(), any())).thenReturn(getClassEnrollData());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.APPROVE_REQUEST)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(approveClassRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Input validation failed"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void approveClassRoomTestWithInvalidJson() throws Exception {
        ApproveClassRequest approveClassRequest = getApproveClassRequest(null, null);

        Mockito.when(teacherService.approveClass(approveClassRequest)).thenReturn(new ApproveClassResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(teacherDao.getEnrollRequestByClassIdAndStudentId(any(), any())).thenReturn(getClassEnrollData());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.APPROVE_REQUEST)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("test case"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Unable to process request at this time"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void approveClassRoomTestWithoutInput() throws Exception {
        ApproveClassRequest approveClassRequest = getApproveClassRequest(null, null);

        Mockito.when(teacherService.approveClass(approveClassRequest)).thenReturn(new ApproveClassResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(teacherDao.getEnrollRequestByClassIdAndStudentId(any(), any())).thenReturn(getClassEnrollData());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.APPROVE_REQUEST)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(""))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Unable to process request at this time"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    protected ApproveClassRequest getApproveClassRequest(Long classId, Long studentId) {
        return ApproveClassRequest.builder()
                .classId(classId)
                .studentId(studentId)
                .build();
    }
}
