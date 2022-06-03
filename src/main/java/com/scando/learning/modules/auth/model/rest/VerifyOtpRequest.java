package com.scando.learning.modules.auth.model.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class VerifyOtpRequest {

    @NotNull(message = "number is required")
    @Pattern(regexp = "^[+][1-9]{2}[0-9]{10}$" , message = "Please provide a valid mobile number")
    @ApiModelProperty(value = "number", example = "+919497174235", required = true, position = 1)
    private String number;


    @NotNull(message = "otp is required")
    @Min(value = 1000 , message = "otp must be 4 digit")
    @Max(value = 9999 , message = "otp must be 4 digit")
    @ApiModelProperty(value = "otp", example = "1234", required = true, position = 2)
    private Integer otp;

    @NotNull(message = "appId is required")
    @ApiModelProperty(value = "appId", example = "2", required = true, position = 2)
    private Long appId;
}
