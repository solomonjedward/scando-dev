package com.scando.learning.common.service.impl;

import com.scando.learning.common.constants.StatusEnum;
import com.scando.learning.common.dao.ApplicationDao;
import com.scando.learning.common.models.Application;
import com.scando.learning.common.models.rest.AppRegRequest;
import com.scando.learning.common.models.rest.AppRegResponse;
import com.scando.learning.common.models.rest.AppRegResponseModel;
import com.scando.learning.common.models.rest.Status;
import com.scando.learning.common.service.AppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AppServiceImpl implements AppService {

    @Autowired
    private ApplicationDao appDao;

    @Override
    @Transactional
    public AppRegResponse saveAppData(AppRegRequest appRegRequest) {

        Application appInfo = appDao.getAppInfoByDeviceId(appRegRequest.getDeviceId());
        if (appInfo == null) {
            appInfo = new Application();
            appInfo.setOsInfo(appRegRequest.getOsInfo());
            appInfo.setDeviceId(appRegRequest.getDeviceId());
            appInfo.setDeviceInfo(appRegRequest.getDeviceInfo());
            appInfo.setAppVersion(appRegRequest.getAppVersion());
            appInfo.setDeviceToken(appRegRequest.getDeviceToken());
            appInfo.setCount(0);
            appInfo = appDao.save(appInfo);
            LOGGER.debug("AppInfo details saved successfully with AppId:{}", appInfo.getAppId());
        } else {
            appInfo.appUpdateSave();
            LOGGER.debug("App Info successfully updated AppId : {}", appInfo.getAppId());
        }
        return AppRegResponse.builder()
                .status(Status.builder()
                        .statusCode(StatusEnum.SUCCESS.getCode()).build())
                .data(AppRegResponseModel.builder().appId(appInfo.getAppId()).build())
                .build();
    }

    @Override
    public Application getAppInfoById(Long appId) {
        return appDao.getAppInfo(appId);
    }
}
