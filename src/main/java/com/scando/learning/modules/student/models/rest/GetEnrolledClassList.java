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
@ApiModel(value = "GetEnrolledClassList")
public class GetEnrolledClassList {
    @ApiModelProperty(value = "classDetails",  position = 1)
    private ClassDetail[] classDetails;
}
