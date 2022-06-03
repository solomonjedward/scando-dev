package com.scando.learning.modules.teacher.models.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "getOwnClasses")
public class GetTeacherClassesResponseModel {

    @ApiModelProperty(value = "classType : 1 - scheduledClass , 2 - nonScheduled class", position = 1)
    private Short classType;

    @ApiModelProperty(value = "classId", position = 1)
    private BigInteger classId;

    @ApiModelProperty(value = "className", position = 2)
    private String className;

    @ApiModelProperty(value = "teacherId", position = 3)
    private BigInteger teacherId;

    @ApiModelProperty(value = "isScheduled", position = 6)
    private Short isScheduled;

    @ApiModelProperty(value = "count of document", position = 7)
    private BigInteger documents;

    @ApiModelProperty(value = "count of doubtSession", position = 8)
    private BigInteger doubtSession;


    @ApiModelProperty(value = "List of days and time sessions" ,position = 3)
    private List<TimeTableSession> timeTable;

    @ApiModelProperty(value = "status message", position = 11)
    private String message;
}
