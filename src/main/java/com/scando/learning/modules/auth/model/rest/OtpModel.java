package com.scando.learning.modules.auth.model.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ApiModel(value = "Otp")
public class OtpModel {

    @ApiModelProperty(value = "Status message", example = "Success", dataType = "String", position = 1)
    private String message;

    @ApiModelProperty(value = "otp code generated", example = "7845", dataType = "Int", position = 2)
    private int otpCode;
}
