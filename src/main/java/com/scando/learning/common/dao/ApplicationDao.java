package com.scando.learning.common.dao;


import com.scando.learning.common.models.Application;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationDao extends AbstractDao<Application> {

    Application getAppInfo(Long appId);

    Application getAppInfoByDeviceId(String deviceId);

    List<String> getFcmToken(Long userId);
}
