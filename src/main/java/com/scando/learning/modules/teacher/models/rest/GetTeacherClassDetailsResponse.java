package com.scando.learning.modules.teacher.models.rest;

import com.scando.learning.common.models.rest.AbstractView;
import com.scando.learning.modules.student.models.rest.ClassDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GetTeacherClassDetailsResponse extends AbstractView {
    List<ClassDetail> data;
}
