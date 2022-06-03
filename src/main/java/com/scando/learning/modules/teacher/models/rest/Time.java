package com.scando.learning.modules.teacher.models.rest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Time {

    @NotNull(message = "hour is required")
    @ApiModelProperty(value = "hour from 00 to 23" , example = "13" , required = true , dataType = "String")
    private String hour;
    @NotNull(message = "min is required")
    @ApiModelProperty(value = "minute from 01 to 60" , example = "30" , required = true , dataType = "String")
    private String min;

}
