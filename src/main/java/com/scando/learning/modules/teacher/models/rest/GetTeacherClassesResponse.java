package com.scando.learning.modules.teacher.models.rest;

import com.scando.learning.common.models.rest.AbstractView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GetTeacherClassesResponse extends AbstractView {
    private List<GetTeacherClassesResponseModel> data;
}
