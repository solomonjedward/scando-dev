package com.scando.learning.modules.teacher.models.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TimeTable {

    @ApiModelProperty(value = "timeTable settings for monday", position = 1)
    private List<TimeTableSession> monday;

    @ApiModelProperty(value = "timeTable settings for tuesday", position = 2)
    private List<TimeTableSession> tuesday;

    @ApiModelProperty(value = "timeTable settings for wednesday", position = 3)
    private List<TimeTableSession> wednesday;

    @ApiModelProperty(value = "timeTable settings for thursday", position = 4)
    private List<TimeTableSession> thursday;

    @ApiModelProperty(value = "timeTable settings for friday", position = 5)
    private List<TimeTableSession> friday;

    @ApiModelProperty(value = "timeTable settings for saturday", position = 6)
    private List<TimeTableSession> saturday;

    @ApiModelProperty(value = "timeTable settings for sunday", position = 7)
    private List<TimeTableSession> sunday;
}
