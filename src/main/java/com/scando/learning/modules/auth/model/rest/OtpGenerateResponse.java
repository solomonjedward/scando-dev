package com.scando.learning.modules.auth.model.rest;

import com.scando.learning.common.models.rest.AbstractView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OtpGenerateResponse extends AbstractView {

    private OtpModel data;
}
