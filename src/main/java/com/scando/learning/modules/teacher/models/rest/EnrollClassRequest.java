package com.scando.learning.modules.teacher.models.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EnrollClassRequest {

    @NotNull(message = "classId is required")
    @ApiModelProperty(value = "classId", example = "100000008", required = true, position = 1)
    private Long classId;

    @NotNull(message = "studentId is required")
    @ApiModelProperty(value = "studentId", required = true, position = 2)
    private Long studentId;
}
