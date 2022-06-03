package com.scando.learning.common.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scando.learning.common.exception.ScandoException;
import com.scando.learning.common.models.rest.AppRegRequest;
import com.scando.learning.common.models.rest.AppRegResponse;
import com.scando.learning.common.models.rest.AppRegResponseModel;
import com.scando.learning.common.models.rest.Status;

public class AbstractControllerTest {

    AppRegRequest getAppRegRequest(String deviceId, String deviceInfo, String deviceToken, String osInfo,
                                   String appVersion) {
        return AppRegRequest.builder()
                .appVersion(appVersion)
                .deviceId(deviceId)
                .deviceInfo(deviceInfo)
                .deviceToken(deviceToken)
                .osInfo(osInfo)
                .build();

    }

    AppRegResponse getAppRegResponse() {
        return AppRegResponse.builder()
                .status(getStatus())
                .data(AppRegResponseModel.builder()
                        .appId(5L)
                        .build()).build();
    }

    Status getStatus() {
        return Status.builder()
                .statusCode(20001)
                .build();
    }

    protected String getJson(Object object) throws ScandoException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException var3) {
            throw new ScandoException("Exception while converting object to json", var3);
        }
    }


}
