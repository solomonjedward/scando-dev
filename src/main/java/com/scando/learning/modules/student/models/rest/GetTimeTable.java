package com.scando.learning.modules.student.models.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ApiModel(value = "GetTimeTable")
public class GetTimeTable {
    @ApiModelProperty(value = "timetable Id", example = "12", dataType = "Long", position = 1)
    private Long timeTableId;

    @ApiModelProperty(value = "classRoom Id",  example = "12",dataType = "Long", position = 2)
    private Long classRoomId;

    @ApiModelProperty(value = "List of days in week ", example = "[1,7]", dataType = "List",position = 3)
    private List<Integer> days;

    @ApiModelProperty(value = "status to check timetable  is repeated", dataType = "boolean", example = "true", position = 4)
    private boolean repeatEnabled;
}
