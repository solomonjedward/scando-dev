package com.scando.learning.modules.auth.model.rest;

import com.scando.learning.common.models.rest.AbstractView;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CheckResponse extends AbstractView {

    private Check data;
}
