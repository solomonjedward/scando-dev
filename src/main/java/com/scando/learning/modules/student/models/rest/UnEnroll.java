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
@ApiModel(value = "UnEnroll")
public class UnEnroll {
    @ApiModelProperty(value = "status code", dataType = "Long", example = "2001", position = 1)
    private Integer code;

    @ApiModelProperty(value = "message", dataType = "String", example = "class enrollment request sent", position = 2)
    private String message;

    @ApiModelProperty(value = "enrollment Id", dataType = "Long", example = "1", position = 3)
    private Long enrollId;
}
