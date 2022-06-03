package com.scando.learning.modules.teacher.models.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class TimeTableSession {

    @NotNull(message = "day is required")
    @ApiModelProperty(value = "day name for session", example = "01", required = true, position = 3)
    private String day;

    @NotNull(message = "start is required")
    @ApiModelProperty(value = "start time in hour and minute" , required = true)
    private Time start;

    @ApiModelProperty(value = "end time in hour and minute" , required = true)
    @NotNull(message = "end is required")
    private Time end;
}
