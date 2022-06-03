package com.scando.learning.common.repository;

import com.scando.learning.common.models.ArchiveClassEnroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveClassEnrollRepository extends JpaRepository<ArchiveClassEnroll, Long> {
}
