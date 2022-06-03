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
@ApiModel(value = "StudentSignUp")
public class StudentSignUp {
    @ApiModelProperty(value = "student Id", dataType = "Long", position = 1)
    private Long studentId;

    @ApiModelProperty(value = "Status message", example = "Success", dataType = "String", position = 2)
    private String message;

}
