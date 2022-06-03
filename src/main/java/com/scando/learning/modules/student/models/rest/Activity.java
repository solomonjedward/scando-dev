package com.scando.learning.modules.student.models.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
public class Activity {

    @ApiModelProperty(value = "doubt sessions", example = "1", dataType = "int", position = 1)
    private BigInteger doubtSession;

    @ApiModelProperty(example = "1", dataType = "int", position = 2)
    private int scheduledTest;

    @ApiModelProperty(example = "1", dataType = "int", position = 3)
    private int homeWork;

    @ApiModelProperty(example = "1", dataType = "int", position = 4)
    private int notice;
}
