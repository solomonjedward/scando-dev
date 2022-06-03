package com.scando.learning.modules.student.models.rest;

import com.scando.learning.common.models.rest.AbstractView;
import com.scando.learning.modules.teacher.models.rest.GetTimeTableResponseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GetTimetableResponse extends AbstractView {

    private GetTimeTableResponseModel data;
}
