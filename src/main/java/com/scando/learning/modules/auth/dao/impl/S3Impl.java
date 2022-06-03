package com.scando.learning.modules.auth.dao.impl;

import com.scando.learning.common.models.rest.S3RequestDetails;
import com.scando.learning.common.repository.S3RequestRepository;
import com.scando.learning.modules.auth.dao.S3Dao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class S3Impl implements S3Dao {
    @Autowired
    S3RequestRepository s3RequestRepository;

    @Override
    public S3RequestDetails save(S3RequestDetails s3RequestDetails) {
        LOGGER.debug("user details: {}",s3RequestDetails);
        return s3RequestRepository.save(s3RequestDetails);
    }

    @Override
    public void delete(S3RequestDetails s3RequestDetails) {
        s3RequestRepository.delete(s3RequestDetails);
    }

    @Override
    public S3RequestDetails getByRequestId(Long requestId) {
        return s3RequestRepository.getByRequestId(requestId);
    }
}
