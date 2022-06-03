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
@ApiModel(value = "VerifyOtp")
public class VerifyOtp {

    @ApiModelProperty(value = "0 - login , 1 - verify" , example =  "1", dataType = "Integer" , position = 1)
    private  Integer code;
    @ApiModelProperty(value = "Status message", example = "Success", dataType = "String", position = 2)
    private String message;

}
