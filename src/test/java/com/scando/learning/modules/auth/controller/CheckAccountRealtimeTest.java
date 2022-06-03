package com.scando.learning.modules.auth.controller;

import com.scando.learning.LearningApplication;
import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.modules.auth.model.rest.CheckRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = LearningApplication.class)
public class CheckAccountRealtimeTest extends AbstractAuthControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @Test
    void withValidMobileNumber() throws Exception {
        CheckRequest checkRequest = getCheckRequest("+919497174288",100L);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.USER_CHECK_ACCOUNT)
                .content(getJson(checkRequest))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.message")
                        .value("App not registered"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
