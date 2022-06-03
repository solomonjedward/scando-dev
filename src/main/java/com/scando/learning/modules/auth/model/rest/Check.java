package com.scando.learning.modules.auth.model.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ApiModel(value = "Check")
public class Check {

    @ApiModelProperty(value = "Message", example = "exists")
    String status;
    @ApiModelProperty(value = "code", example = "1")
    Integer code;
}
