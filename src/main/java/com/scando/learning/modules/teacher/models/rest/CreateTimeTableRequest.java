package com.scando.learning.modules.teacher.models.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTimeTableRequest {


    @NotNull(message = "classId is required")
    @ApiModelProperty(value = "classId", required = true, example = "100000001" , position = 1)
    private Long classId;

    @NotNull(message = "teacherId is required")
    @ApiModelProperty(value = "teacherId", required = true, example = "1",position = 2)
    private Long teacherId;

    @NotNull(message = "timetable is required")
    @ApiModelProperty(value = "List of days and time sessions" ,position = 6)
    private List<TimeTableSession> timeTable;

    @NotNull(message = "repeatEnabled is required")
    @ApiModelProperty(value = "repeatEnabled",example ="false", required = true, position = 5)
    private boolean repeatEnabled;

}
