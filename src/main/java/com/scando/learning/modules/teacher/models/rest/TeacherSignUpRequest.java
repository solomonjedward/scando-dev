package com.scando.learning.modules.teacher.models.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class TeacherSignUpRequest {

    @NotNull(message = "Mobile Number is required")
    @Pattern(regexp = "^[+][1-9]{2}[0-9]{10}$", message = "Please provide a valid mobile number")
    @ApiModelProperty(value = "mobileNumber", required = true, position = 1)
    private String number;

    @NotNull(message = "User Name is required")
    @ApiModelProperty(value = "userName", required = true, position = 2)
    private String userName;

    @NotNull(message = "userType is required")
    @ApiModelProperty(value = "userType", required = true, position = 1)
    private Integer userType;

    @NotNull(message = "userType is required")
    @ApiModelProperty(value = "userType", required = true, position = 1)
    private Integer userStatus;

    @NotNull(message = "subjectCode  is required")
    @ApiModelProperty(value = "subjectCode", required = true, position = 3)
    private Long[] subjectCode;

    @NotNull(message = "Profile Image is required")
    //@Pattern(regexp = "(^(\\d{1,3}\\.){3}\\d{1,3}$)(^[a-z0-9]([a-z0-9-]*(\\.[a-z0-9])?)*$)", message = "Please provide a Url")
    @ApiModelProperty(value = "profileImage", required = true, position = 4)
    private String profile_url;

    @ApiModelProperty(value = "App Id" , example = "1" , required = true ,position = 5)
    private Long appId;

}
