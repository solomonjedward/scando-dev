package com.scando.learning.modules.teacher.models.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TeacherUpdateRequest {

    @NotNull(message = "userId is required")
    @ApiModelProperty(value = "userId", required = true, position = 1)
    private Long userId;

    @NotNull(message = "User Name is required")
    @ApiModelProperty(value = "userName", required = true, position = 2)
    private String userName;

    @NotNull(message = "subjectCode  is required")
    @ApiModelProperty(value = "subjectCode", required = true, position = 3)
    private Long[] subjectCode;

    @NotNull(message = "Profile Image is required")
    @ApiModelProperty(value = "profileImage", required = true, position = 4)
    private String profile_url;

}
