package com.scando.learning.modules.auth.dao;

import com.scando.learning.common.dao.AbstractDao;
import com.scando.learning.common.models.rest.S3RequestDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface S3Dao extends AbstractDao<S3RequestDetails> {

    S3RequestDetails getByRequestId(Long requestId);
}
