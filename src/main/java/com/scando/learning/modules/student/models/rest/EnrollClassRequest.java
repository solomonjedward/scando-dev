package com.scando.learning.modules.student.models.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EnrollClassRequest {

    @NotNull(message = "classId is required")
    @ApiModelProperty(value = "classId", required = true, example = "100000008", position = 1)
    private Long classId;
}
