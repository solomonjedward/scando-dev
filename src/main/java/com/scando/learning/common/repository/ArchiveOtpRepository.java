package com.scando.learning.common.repository;

import com.scando.learning.common.models.ArchiveOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveOtpRepository extends JpaRepository<ArchiveOtp, Long> {
}
