package com.scando.learning.common.repository;

import com.scando.learning.common.models.StudyMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyMaterialRepository extends JpaRepository<StudyMaterial, Long> {

    void deleteByClassId(Long classId);
}
