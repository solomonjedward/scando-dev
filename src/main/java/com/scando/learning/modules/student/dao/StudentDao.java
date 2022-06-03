package com.scando.learning.modules.student.dao;

import com.scando.learning.common.models.ArchiveClassEnroll;
import com.scando.learning.modules.student.models.rest.ClassDetail;
import com.scando.learning.modules.student.models.rest.GetEnrollListRequest;
import com.scando.learning.modules.student.models.rest.GetStudentClassesResponseModel;
import com.scando.learning.modules.student.models.rest.StudentEnrollDetails;
import com.scando.learning.modules.teacher.models.ClassEnroll;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {

    List<GetStudentClassesResponseModel> getStudentClassOnSpecificDay(Long userId, String day);

    ClassEnroll getEnrolledClassByClassIdAndStudentId(Long classId, Long studentId);

    ClassEnroll enrollClass(ClassEnroll classEnroll);

    List<ClassDetail> getStudentSpecificClassDetails(Long classId);

    List<ClassDetail> getStudentAllClassDetails(Long userId, Long isScheduled);

    void deleteClassEnroll(ClassEnroll classEnroll);

    ArchiveClassEnroll saveArchiveClassEnroll(ArchiveClassEnroll archiveClassEnroll);

    Page<StudentEnrollDetails> getEnrollList(GetEnrollListRequest enrollListRequest);

}
