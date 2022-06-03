package com.scando.learning.modules.auth.model.rest;

import com.scando.learning.common.models.rest.AbstractView;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class VerifyOtpResponse<T> extends AbstractView {

   private T data;
}
