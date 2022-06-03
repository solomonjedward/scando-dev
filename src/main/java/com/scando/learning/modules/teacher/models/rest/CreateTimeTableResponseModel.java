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
@ApiModel(value = "CreateTimeTable")
public class CreateTimeTableResponseModel {

    @ApiModelProperty(value = "message", example = "Time table saved successfully", dataType = "String", position = 1)
    private String message;
}
