package com.scando.learning.modules.auth.model.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OtpGenerateRequest {

    @NotNull(message = "Mobile Number is required")
    @Pattern(regexp = "^[+][1-9]{2}[0-9]{10}$" , message = "Please provide a valid mobile number")
    @ApiModelProperty(value = "number", example = "+911234567890",required = true, position = 1)
    private String number;

    @NotNull(message = "AppId cannot be null")
    @ApiModelProperty(value = "app Id", example = "1", dataType = "Long", required = true, position = 2)
    private Long appId;
}
