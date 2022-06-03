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
@ApiModel(value = "ApproveClasses")
public class ApproveClassResponseModel {

    @ApiModelProperty(value = "student Id", example = "1",dataType = "Long", position = 3)
    private Long studentId;

    @ApiModelProperty(value = "classRoom Id", example = "12", dataType = "Long", position = 2)
    private Long classId;

    @ApiModelProperty(value = "message", example = "class approved", position = 1)
    private String message;
}
