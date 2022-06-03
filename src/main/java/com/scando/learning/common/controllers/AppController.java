package com.scando.learning.common.controllers;

import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.common.constants.ErrorCodeEnum;
import com.scando.learning.common.exception.ControllerException;
import com.scando.learning.common.models.SwaggerHeads;
import com.scando.learning.common.models.rest.AppRegRequest;
import com.scando.learning.common.models.rest.AppRegResponse;
import com.scando.learning.common.models.rest.Status;
import com.scando.learning.common.service.AppService;
import com.scando.learning.common.utils.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@Api(tags = {SwaggerHeads.APP_REGISTRATION_API})
@Slf4j
public class AppController {

    @Autowired
    AppService appService;


    @PostMapping(ApiUrls.APP_REGISTRATION)
    @ApiOperation(value = "Api for registering the app")
    public ResponseEntity<AppRegResponse> appRegistration(@RequestBody @Valid AppRegRequest appRegRequest,
                                                          BindingResult bindingResult) {

        Status status = WebUtils.getStatus();
        status.setApiId(1);
        WebUtils.setStatus(status);
        try {

            if (bindingResult.hasErrors()) {
                status.setEndTime(System.currentTimeMillis());
                LOGGER.debug("Input validation failed");
                throw new ControllerException(status, bindingResult, ErrorCodeEnum.APP_REGISTRATION_VALIDATION_FAILED);
            }

            AppRegResponse  appRegResponse= appService.saveAppData(appRegRequest);
            return ResponseEntity.ok(appRegResponse);
        } catch (Exception e) {
            LOGGER.error(Arrays.toString(e.getStackTrace()));
            throw e;
        }
    }
}
