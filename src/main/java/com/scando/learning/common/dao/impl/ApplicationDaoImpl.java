package com.scando.learning.common.dao.impl;


import com.scando.learning.common.dao.ApplicationDao;
import com.scando.learning.common.models.Application;
import com.scando.learning.common.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApplicationDaoImpl implements ApplicationDao {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public Application getAppInfo(Long appId) {
        return applicationRepository.getByAppId(appId);
    }

    @Override
    public Application getAppInfoByDeviceId(String deviceId) {
        return applicationRepository.getByDeviceId(deviceId);
    }

    @Override
    public List<String> getFcmToken(Long userId) {
        return applicationRepository.getFcmTokens(userId);
    }

    @Override
    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public void delete(Application application) {
        applicationRepository.delete(application);
    }
}
