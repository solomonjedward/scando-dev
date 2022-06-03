package com.scando.learning.common.repository;

import com.scando.learning.common.models.ClassVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassVideoRepository extends JpaRepository<ClassVideo, Long> {

    void deleteByClassId(Long classId);
}
