package com.scando.learning.modules.student.models.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.scando.learning.modules.teacher.models.rest.TimeTableSession;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
public class ClassDetail {

    @ApiModelProperty(value = "classRoom Id", example = "12", dataType = "Long", position = 1)
    private BigInteger classId;

    @ApiModelProperty(value = "class name", example = "Maths X", dataType = "String", position = 1)
    private String className;

    @ApiModelProperty(value = "subject name", example = "Maths", dataType = "String", position = 1)
    private String subjectName;

    @ApiModelProperty(value = "classRoom Id", example = "12", dataType = "Long", position = 1)
    private Short classType;

    @ApiModelProperty(value = "isScheduled", example = "1", dataType = "Short", position = 3)
    private Short isScheduled;

    @ApiModelProperty(value = "Activities in class", position = 6)
    private Activity activity;

    @ApiModelProperty(value = "Study materials in class", position = 7)
    private StudyMaterials studyMaterials;

    @ApiModelProperty(value = "Count of class Recording", position = 8)
    private Long classRecordings;

    @ApiModelProperty(value = "List of days and time sessions", position = 3)
    private List<TimeTableSession> timeTable;

    @ApiModelProperty(value = "isEnrolled", example = "1", dataType = "Short", position = 3)
    private Short isEnrolled;

    @ApiModelProperty(value = "status message", position = 9)
    private String message;
}
