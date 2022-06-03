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
@ApiModel(value = "UnEnroll")
public class UnEnrollResponseModel {

    @ApiModelProperty(value = "status", dataType = "String", example = "Classroom un enrolled", position = 1)
    private String status;

    @ApiModelProperty(value = "classRoom Id", example = "2001", dataType = "Integer", position = 2)
    private Integer code;
}
