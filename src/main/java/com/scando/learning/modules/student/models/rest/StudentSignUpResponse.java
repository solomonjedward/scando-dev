package com.scando.learning.modules.student.models.rest;

import com.scando.learning.common.models.rest.AbstractView;
import com.scando.learning.modules.auth.model.rest.Login;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StudentSignUpResponse extends AbstractView {

    Login data;
}
