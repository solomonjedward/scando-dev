package com.scando.learning.common.repository;

import com.scando.learning.modules.teacher.models.ClassEnroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassEnrollRepository extends JpaRepository<ClassEnroll, Long> {

    ClassEnroll getByClassIdAndStudentId(Long classId, Long studentId);

    List<ClassEnroll> getByClassId(Long classId);

    void deleteByEnrollId(Long enrollId);
}
