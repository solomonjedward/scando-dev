package com.scando.learning.modules.auth.model.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Login {

    @ApiModelProperty(value = "0 - login , 1 - verify", example = "1", dataType = "Integer", position = 1)
    private Integer code;
    @ApiModelProperty(value = "App Id", example = "5", dataType = "Long", position = 2)
    private Long appId;

    @ApiModelProperty(value = "User Id", example = "1", dataType = "Long", position = 3)
    private Long userId;

    @ApiModelProperty(value = "User Name", example = "joseph", dataType = "String", position = 4)
    private String userName;

    @ApiModelProperty(value = "User Type [0 -teacher , 1 -student]", example = "0", dataType = "Long", position = 5)
    private Integer userType;

    @ApiModelProperty(value = "jwt token", example = "jdsaf;oifhdawehfasdvjksdhf34853dhfafhd345rfda",
            dataType = "String", position = 6)
    private String token;

    @ApiModelProperty(value = "refresh token", example = "jdsafjjj;;oifhdawehfasdvjksdhf34853dhfafhd345rfda",
            dataType = "String", position = 7)
    private String refreshToken;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(value = "profile Url", example = "<3 bucket link to the image>", dataType = "String",
            position = 8)
    private String profileUrl;
}
