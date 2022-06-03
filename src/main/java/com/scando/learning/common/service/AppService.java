package com.scando.learning.common.service;

import com.scando.learning.common.models.Application;
import com.scando.learning.common.models.rest.AppRegRequest;
import com.scando.learning.common.models.rest.AppRegResponse;
import org.springframework.stereotype.Service;


public interface AppService {

    AppRegResponse saveAppData(AppRegRequest appRegRequest);

    Application getAppInfoById(Long appId);
}
