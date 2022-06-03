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
@ApiModel(value = "GetEnrollList")
public class GetEnrollListResponseModel {

    @ApiModelProperty(value = "Enrollment details", position = 1)
    private EnrollDetails[] enrollDetails;

}
