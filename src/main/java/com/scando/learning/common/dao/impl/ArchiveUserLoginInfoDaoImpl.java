package com.scando.learning.common.dao.impl;

import com.scando.learning.common.dao.ArchiveUserLoginInfoDao;
import com.scando.learning.common.models.ArchiveUserLoginInfo;
import com.scando.learning.common.repository.ArchiveUserLoginInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArchiveUserLoginInfoDaoImpl implements ArchiveUserLoginInfoDao {

    @Autowired
    ArchiveUserLoginInfoRepository archiveUserLoginInfoRepository;
    @Override
    public ArchiveUserLoginInfo save(ArchiveUserLoginInfo object) {
        return archiveUserLoginInfoRepository.save(object);
    }

    @Override
    public void delete(ArchiveUserLoginInfo object) {
       archiveUserLoginInfoRepository.delete(object);
    }
}
