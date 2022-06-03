package com.scando.learning.common.repository;

import com.scando.learning.common.models.ClassDoubt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassDoubtRepository extends JpaRepository<ClassDoubt, Long> {

    void deleteByClassId(Long classId);
}
