package com.scando.learning.modules.student.models.rest;

import com.scando.learning.common.models.rest.AbstractListRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetEnrollListRequest extends AbstractListRequest {

    @ApiModelProperty(value = "enroll status of enrollRequest list, 1:enrolled 0:un enrolled", example = "1", position = 2)
    private Integer enrollStatus;
}
