package com.scando.learning.modules.student.models.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@Data
public class StudentSignUpRequest {

    @NotNull(message = "userName is required")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,55}$" , message = "username doesn't meet the criteria")
    @ApiModelProperty(value = "userName", example = "jonthan404" , required = true, position = 1)
    private String userName;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[+][1-9]{2}[0-9]{10}$" , message = "Please provide a valid mobile number")
    @ApiModelProperty(example = "+919498741259", required = true, position = 2,value = "Mobile Number")
    private String number;

    @Min(value = 0, message = "Invalid profileId")
    @ApiModelProperty(value = "Id of the uploaded profile" , example = "1" , required = true ,position = 3)
    private Long profileId;

    @NotNull
    @ApiModelProperty(value = "App Id" , example = "1" , required = true ,position = 4)
    private Long appId;

}
