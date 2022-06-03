package com.scando.learning.modules.teacher.models.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ApiModel(value = "TimeTableResponse")
public class GetTimeTableResponseModel {


    @ApiModelProperty(value = "classRoom Id",  example = "100000001",dataType = "Long", position = 1)
    private Long classRoomId;

    @ApiModelProperty(value = "teacher Id" , example = "1" , dataType = "Long" , position = 2)
    private Long teacherId;

    @ApiModelProperty(value = "List of days and time sessions" ,position = 3)
    private List<TimeTableSession> timeTable;


/*    @ApiModelProperty(value = "status to check timetable  is repeated", dataType = "boolean", example = "true", position = 4)
    private Integer repeatEnabled;*/
}
