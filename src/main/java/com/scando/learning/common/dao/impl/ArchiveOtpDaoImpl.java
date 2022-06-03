package com.scando.learning.common.dao.impl;

import com.scando.learning.common.dao.ArchiveOtpDao;
import com.scando.learning.common.models.ArchiveOtp;
import com.scando.learning.common.repository.ArchiveOtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArchiveOtpDaoImpl implements ArchiveOtpDao {

    @Autowired
    ArchiveOtpRepository archiveOtpRepository;


    @Override
    public ArchiveOtp save(ArchiveOtp object) {

        return archiveOtpRepository.save(object);
    }

    @Override
    public void delete(ArchiveOtp object) {

        archiveOtpRepository.delete(object);
    }
}
