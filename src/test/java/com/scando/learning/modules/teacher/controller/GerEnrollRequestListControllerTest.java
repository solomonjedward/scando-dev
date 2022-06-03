package com.scando.learning.modules.teacher.controller;

import com.scando.learning.LearningApplication;
import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.common.constants.StatusEnum;
import com.scando.learning.modules.teacher.dao.TeacherDao;
import com.scando.learning.modules.teacher.models.rest.EnrollDetails;
import com.scando.learning.modules.teacher.models.rest.GetEnrollListRequest;
import com.scando.learning.modules.teacher.models.rest.GetEnrollListResponse;
import com.scando.learning.modules.teacher.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = LearningApplication.class)
public class GerEnrollRequestListControllerTest extends AbstractTeacherControllerTest{

    @MockBean
    private TeacherService teacherService;

    @MockBean
    private TeacherDao teacherDao;

    @Test
    void getEnrollRequestListWithValidData() throws Exception {

        GetEnrollListRequest enrollListRequest = new GetEnrollListRequest();
        enrollListRequest.setClassId(1L);

        Mockito.when(teacherService.getApprovalList(enrollListRequest)).thenReturn(new GetEnrollListResponse());
        when(teacherDao.getEnrollRequestList(any(GetEnrollListRequest.class))).thenReturn(getEnrollListResponsePage(0,20,4));

        mockMvc.perform(get(ApiUrls.GET_APPROVAL_LIST)
                        .header("Authorization", "bearer token")
                        .header("isDebug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJson(enrollListRequest))
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

   /* @Test
    void getEnrollRequestListWithInvalidJson() throws Exception {

        GetEnrollListRequest enrollListRequest = new GetEnrollListRequest();
        enrollListRequest.setClassId(1L);

        Mockito.when(teacherService.getApprovalList(enrollListRequest)).thenReturn(new GetEnrollListResponse());
        when(teacherDao.getEnrollRequestList(any(GetEnrollListRequest.class))).thenReturn(getEnrollListResponsePage(0,20,4));
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(get(ApiUrls.GET_APPROVAL_LIST)
                        .header("Authorization", "bearer token")
                        .header("isDebug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("getJson(enrollListRequest)")
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Unable to process request at this time"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getEnrollRequestListWithoutInput() throws Exception {

        GetEnrollListRequest enrollListRequest = new GetEnrollListRequest();
        enrollListRequest.setClassId(null);

        Mockito.when(teacherService.getApprovalList(enrollListRequest)).thenReturn(new GetEnrollListResponse());
        when(teacherDao.getEnrollRequestList(any(GetEnrollListRequest.class))).thenReturn(getEnrollListResponsePage(0,20,4));
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(get(ApiUrls.GET_APPROVAL_LIST)
                        .header("Authorization", "bearer token")
                        .header("isDebug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("Unable to process request at this time"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
*/
    private Page<EnrollDetails> getEnrollListResponsePage(int start, int limit, int listSize) {

        List<EnrollDetails> requestList = new ArrayList<>();
        for (int i=1; i<=listSize; i++) {
            EnrollDetails enrollList = new EnrollDetails();
            enrollList.setEnrollId(1L);
            enrollList.setClassId(1L);
            enrollList.setEnrollTime(1612121212L);
            enrollList.setStudentName("John Doe");
            enrollList.setPhoneNumber("+91123456789");

            requestList.add(enrollList);
        }

        return new PageImpl<>(requestList, PageRequest.of(start/limit, limit), requestList.size());
    }
}
