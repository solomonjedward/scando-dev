
package com.scando.learning.modules.auth.model.rest;



import com.scando.learning.common.models.rest.AbstractView;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckRequest {

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[+][1-9]{2}[0-9]{10}$" , message = "Please provide a valid mobile number")
    @ApiModelProperty(example = "+919498741259")
    private String mobileNumber;

    @NotNull(message = "AppId is required")
    @ApiModelProperty(example = "1", required = true , value =  "app Id" , dataType = "Long")
    private Long appId;


}
