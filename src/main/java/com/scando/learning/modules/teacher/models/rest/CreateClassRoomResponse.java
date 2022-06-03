package com.scando.learning.modules.teacher.models.rest;

import com.scando.learning.common.models.rest.AbstractView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CreateClassRoomResponse extends AbstractView {

    private CreateClassRoomResponseModel data;
}
