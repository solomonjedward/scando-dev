package com.scando.learning.modules.teacher.models.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateClassRoomRequest {

    /*@NotNull(message = "teacherId is required")
    @ApiModelProperty(value = "teacherId", example = "1", position = 1)
    private Long teacherId;*/

    @NotNull(message = "classType is required")
    @ApiModelProperty(value = "classType", example = "1", required = true, position = 2)
    private Integer classType;

    @NotBlank(message = "className  is required")
    @Size(max = 25, min = 3, message = "ClassName cannot exceed 50 characters")//3 to 25 characters
    @ApiModelProperty(value = "className", example = "10 th standard", required = true, position = 3)
    private String className;

    @NotBlank(message = "subjectName is required")//3 to 20
    @Size(max = 20, min = 3, message = "SubjectName cannot exceed 50 characters")
    @ApiModelProperty(value = "subjectName", example = "Maths", required = true, position = 4)
    private String subjectName;

    @NotNull(message = "timetableEnabled is required")
    @ApiModelProperty(value = "timetableEnabled", example = "true", required = true, position = 5)
    private boolean timetableEnabled;

    //Applicable only if timetableEnabled is true
    @ApiModelProperty(value = "List of days and time sessions", position = 6)
    private List<TimeTableSession> timeTable;

    //Applicable only if timetableEnabled is true
    @ApiModelProperty(value = "repeatEnabled",example ="false", position = 7)
    private boolean repeatEnabled;
}
