package com.scando.learning.modules.teacher.models.rest;

import com.scando.learning.modules.student.models.rest.StudyMaterials;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ApiModel(value = "GetClassRoom")
public class GetClassRoomResponseModel {

    @ApiModelProperty(value = "classRoom name", example = "9th maths classroom", dataType = "String", position = 1)
    private String className;

    @ApiModelProperty(value = "boolean status for timetable enabled or not", example = "true",dataType = "boolean", position = 2)
    private boolean timetableEnabled;

    @ApiModelProperty(value = "enrolled students count", example = "12", dataType = "int", position = 3)
    private int enrolledStudents;

    @ApiModelProperty(value = "classRecordings count", example = "13", dataType = "int", position = 4)
    private int classRecordings;

    @ApiModelProperty(value = "StudyMaterials",  position = 5)
    private StudyMaterials studyMaterials;
}
