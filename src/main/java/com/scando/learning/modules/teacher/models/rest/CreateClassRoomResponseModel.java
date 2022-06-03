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
@ApiModel(value = "ClassRoom")
public class CreateClassRoomResponseModel {

    @ApiModelProperty(value = "classRoom Id", example = "12", dataType = "Long", position = 1)
    private Long classRoomId;

    @ApiModelProperty(value = "Message", example = "Classroom created successfully", dataType = "String", position = 2)
    private String status;
}
