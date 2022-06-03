package com.scando.learning.modules.student.models.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentEnrollDetails {

    @ApiModelProperty(value = "Enrollment request id", example = "1", dataType = "Long", position = 1)
    private Long enrollId;

    @ApiModelProperty(value = "Enrollment requested class id", dataType = "Long", example = "1", position = 2)
    private Long classId;

    @ApiModelProperty(value = "Enrollment requested time", example = "1600000000", dataType = "Long", position = 4)
    private Long enrollTime;

    @ApiModelProperty(value = "Enrollment requested class name", example = "Maths Class", dataType = "String", position = 5)
    private String className;

    @ApiModelProperty(value = "Enrollment requested teacherId", example = "1", dataType = "Long", position = 6)
    private Long teacherId;

    @ApiModelProperty(value = "Enrollment status 0:un enrolled, 1: enrolled", example = "1", dataType = "Integer", position = 7)
    private Integer enrollStatus;

    @ApiModelProperty(value = "Enrolled subject name", example = "Maths", dataType = "String", position = 8)
    private String subjectName;
}
