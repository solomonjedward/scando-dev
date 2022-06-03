package com.scando.learning.modules.teacher.models.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ApproveClassRequest {

    @NotNull(message = "classId is required")
    @ApiModelProperty(value = "classId", required = true, position = 1)
    private Long classId;

    @NotNull(message = "studentId is required")
    @ApiModelProperty(value = "studentId", required = true, position = 2)
    private Long studentId;
}
