package com.scando.learning.modules.auth.model.rest;

import com.scando.learning.common.models.rest.AbstractView;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SignUpRequest extends AbstractView {

    @NotNull
    private String username;

    @NotNull
    private String number;

    @NotNull
    private String profileImage;

    @NotNull
    private List<Integer> subjectIds;

}
