package com.scando.learning.modules.auth.model.rest;

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
@ApiModel(value = "ProfileUpload")
public class ProfileUpload {
    @ApiModelProperty(value = "Status message", example = "Success", dataType = "String", position = 1)
    private String message;

    @ApiModelProperty(value = "User profile image url", dataType = "java.lang.String", position = 2)
    private String profileUrl;

    @ApiModelProperty(value = "requestUrlId", dataType = "java.lang.Long", position = 3)
    private Long requestUrlId;
}
