package com.scando.learning.modules.student.models.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ApiModel(value = "EnrollClass")
public class EnrollClass {
    @ApiModelProperty(value = "enrollment Id", example = "12", dataType = "Long", position = 1)
    private Long enrollId;

    @ApiModelProperty(value = "Status message", example = "Success", dataType = "String", position = 2)
    private String message;

    @ApiModelProperty(value = "Response status code from the api", example = "20001", dataType = "Int", position = 3)
    private int code;
}
