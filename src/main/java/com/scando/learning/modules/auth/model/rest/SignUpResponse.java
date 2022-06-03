package com.scando.learning.modules.auth.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scando.learning.common.models.rest.AbstractView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUpResponse extends AbstractView {

    @ApiModelProperty(value = "Status message", example = "Success", dataType = "String", position = 1)
    private String message;

    @ApiModelProperty(value = "teacher Id", dataType = "Integer", example = "21", position = 2)
    private Integer teacherId;
}
