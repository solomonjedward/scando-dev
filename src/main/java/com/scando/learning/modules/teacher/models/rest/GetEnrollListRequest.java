package com.scando.learning.modules.teacher.models.rest;

import com.scando.learning.common.models.rest.AbstractListRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GetEnrollListRequest extends AbstractListRequest {

    @ApiModelProperty(value = "classId of enrollRequest list", example = "1", position = 1)
    private Long classId;

    @ApiModelProperty(value = "enroll status of enrollRequest list, 1:enrolled 0:un enrolled", example = "1", position = 2)
    private Integer enrollStatus;
}
