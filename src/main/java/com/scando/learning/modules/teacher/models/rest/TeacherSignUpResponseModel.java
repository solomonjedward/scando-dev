package com.scando.learning.modules.teacher.models.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ApiModel(value = "TeacherSignup")
public class TeacherSignUpResponseModel {

    @ApiModelProperty(value = "0 - login , 1 - verify", example = "1", dataType = "Integer", position = 1)
    private Integer code;

    @ApiModelProperty(value = "App Id", example = "5", dataType = "Long", position = 2)
    private Long appId;

    @ApiModelProperty(value = "User Id", example = "1", dataType = "Long", position = 3)
    private Long userId;

    @ApiModelProperty(value = "User Type [0 -teacher , 1 -student]", example = "0", dataType = "Long", position = 4)
    private Integer userType;

    @ApiModelProperty(value = "User name", example = "test", dataType = "String", position = 5)
    private String userName;

    @ApiModelProperty(value = "profileUrl", example = "String", dataType = "String", position = 6)
    private String  profileUrl;

    @ApiModelProperty(value = "jwt token", example = "jdsaf;oifhdawehfasdvjksdhf34853dhfafhd345rfda",
            dataType = "String", position = 7)
    private String token;

    @ApiModelProperty(value = "refresh token", example = "jdsafjjj;;oifhdawehfasdvjksdhf34853dhfafhd345rfda",
            dataType = "String", position = 8)
    private String refreshToken;

    @ApiModelProperty(value = "Message", example = "User created successfully", dataType = "String", position = 9)
    private String status;
}
