package com.scando.learning.modules.teacher.controller;

import com.scando.learning.LearningApplication;
import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.common.constants.StatusEnum;
import com.scando.learning.modules.teacher.dao.TeacherDao;
import com.scando.learning.modules.teacher.models.rest.CreateClassRoomRequest;
import com.scando.learning.modules.teacher.models.rest.CreateClassRoomResponse;
import com.scando.learning.modules.teacher.models.rest.DeleteClassRoomRequest;
import com.scando.learning.modules.teacher.models.rest.DeleteClassRoomResponse;
import com.scando.learning.modules.teacher.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = LearningApplication.class)
public class DeleteClassRoomControllerTest extends AbstractTeacherControllerTest{

    @MockBean
    private TeacherService teacherService;

    @MockBean
    private TeacherDao teacherDao;

    @Test
    void deleteClassRoomTestWithValidData() throws Exception{
        DeleteClassRoomRequest deleteClassRoomRequest = new DeleteClassRoomRequest();
        deleteClassRoomRequest.setClassId(1L);

        Mockito.when(teacherService.deleteClassRoom(deleteClassRoomRequest)).thenReturn(new DeleteClassRoomResponse());

        when(teacherDao.getClassRoomByClassId(any())).thenReturn(getClassRoom());
        when(teacherDao.getClassEnrollByClassId(any())).thenReturn(getClassEnrollList());

        mockMvc.perform(MockMvcRequestBuilders.delete(ApiUrls.DELETE_CLASSROOM)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(deleteClassRoomRequest))).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteClassRoomTestWithInvalidData() throws Exception{
        DeleteClassRoomRequest deleteClassRoomRequest = new DeleteClassRoomRequest();
        deleteClassRoomRequest.setClassId(null);

        Mockito.when(teacherService.deleteClassRoom(deleteClassRoomRequest)).thenReturn(new DeleteClassRoomResponse());

//        when(teacherDao.getTimeTableForClass(any())).thenReturn(getTimeTable());
        when(teacherDao.getClassEnrollByClassId(any())).thenReturn(getClassEnrollList());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete(ApiUrls.DELETE_CLASSROOM)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(getJson(deleteClassRoomRequest))).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteClassRoomTestWithInvalidJson() throws Exception{
        DeleteClassRoomRequest deleteClassRoomRequest = new DeleteClassRoomRequest();
        deleteClassRoomRequest.setClassId(1L);

        Mockito.when(teacherService.deleteClassRoom(deleteClassRoomRequest)).thenReturn(new DeleteClassRoomResponse());

//        when(teacherDao.getTimeTableForClass(any())).thenReturn(getTimeTable());
        when(teacherDao.getClassEnrollByClassId(any())).thenReturn(getClassEnrollList());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete(ApiUrls.DELETE_CLASSROOM)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("sss")).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteClassRoomTestWithoutInput() throws Exception{
        DeleteClassRoomRequest deleteClassRoomRequest = new DeleteClassRoomRequest();
        deleteClassRoomRequest.setClassId(null);

        Mockito.when(teacherService.deleteClassRoom(deleteClassRoomRequest)).thenReturn(new DeleteClassRoomResponse());

//        when(teacherDao.getTimeTableForClass(any())).thenReturn(getTimeTable());
        when(teacherDao.getClassEnrollByClassId(any())).thenReturn(getClassEnrollList());
        when(securityInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete(ApiUrls.DELETE_CLASSROOM)
                        .header("Authorization","Bearer token")
                        .header("debug", false)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("")).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.statusCode").value(StatusEnum.FAILED.getCode()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
