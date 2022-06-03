package com.scando.learning.modules.auth.model.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProfileUploadRequest {
    @ApiModelProperty(value = "API profile image", dataType = "org.springframework.web.multipart.MultipartFile",
            required = true, position = 1)
    private MultipartFile multipartFile;
}
