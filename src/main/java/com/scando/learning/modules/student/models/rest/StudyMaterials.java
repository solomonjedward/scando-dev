package com.scando.learning.modules.student.models.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyMaterials {

    @ApiModelProperty(example = "1", dataType = "int", position = 1)
    private BigInteger documentCount;

    @ApiModelProperty(example = "1", dataType = "int", position = 2)
    private BigInteger voiceCount;

    @ApiModelProperty(example = "1", dataType = "int", position = 3)
    private BigInteger videoCount;

}
