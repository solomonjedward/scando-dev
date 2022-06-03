package com.scando.learning.modules.teacher.models.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DeleteClassRoomRequest {

    @NotNull(message = "classId is required")
    @ApiModelProperty(value = "classId", required = true, position = 1)
    private Long classId;
}
