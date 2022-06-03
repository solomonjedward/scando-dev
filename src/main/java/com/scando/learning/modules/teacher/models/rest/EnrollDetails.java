package com.scando.learning.modules.teacher.models.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnrollDetails {

    @ApiModelProperty(value = "Enrollment request id", example = "1", dataType = "Long", position = 1)
    private Long enrollId;

    @ApiModelProperty(value = "Enrollment requested class id", dataType = "Long", example = "1", position = 2)
    private Long classId;

    @ApiModelProperty(value = "Enrollment requested student id", example = "1", dataType = "Long",position = 3)
    private Long studentId;

    @ApiModelProperty(value = "Enrollment requested time", example = "1600000000", dataType = "Long", position = 4)
    private Long enrollTime;

    @ApiModelProperty(value = "Enrollment requested student name", example = "John Doe", dataType = "String", position = 5)
    private String studentName;

    @ApiModelProperty(value = "Enrollment requested student contact number", example = "+918848194041", dataType = "String", position = 6)
    private String phoneNumber;

    @ApiModelProperty(value = "Enrollment status 0:un enrolled, 1: enrolled", example = "1", dataType = "Integer", position = 7)
    private Integer enrollStatus;
}
