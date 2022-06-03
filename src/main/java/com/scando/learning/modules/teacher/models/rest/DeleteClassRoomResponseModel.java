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
@ApiModel(value = "DeleteClassRoom")
public class DeleteClassRoomResponseModel {

    @ApiModelProperty(value = "Status message", example = "Success", dataType = "String", position = 1)
    private String message;

    @ApiModelProperty(value = "Response status code from the api", example = "20001", dataType = "Int", position = 2)
    private int code;
}
