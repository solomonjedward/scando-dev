package com.scando.learning.modules.student.models.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.scando.learning.modules.teacher.models.rest.TimeTableSession;
import io.swagger.annotations.ApiModel;
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
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "getOwnClasses")
public class GetStudentClassesResponseModel {
    @ApiModelProperty(value = "classType : 1 - scheduledClass , 2 - nonScheduled class", position = 1)
    private Short classType;

    @ApiModelProperty(value = "classId", position = 1)
    private BigInteger classId;

    @ApiModelProperty(value = "className", position = 2)
    private String className;

    @ApiModelProperty(value = "scheduledDays", position = 5)
    private List<Long> days;

    @ApiModelProperty(value = "count of document", position = 6)
    private BigInteger documents;

    @ApiModelProperty(value = "count of doubtSession", position = 6)
    private Long doubtSession;

    @ApiModelProperty(value = "List of days and time sessions" ,position = 3)
    private List<TimeTableSession> timeTable;

    @ApiModelProperty(value = "status message", position = 7)
    private String message;
}
