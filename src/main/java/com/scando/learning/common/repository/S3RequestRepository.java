package com.scando.learning.common.repository;

import com.scando.learning.common.models.rest.S3RequestDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S3RequestRepository extends JpaRepository<S3RequestDetails, Long> {

    S3RequestDetails getByRequestId(Long requestId);
}
