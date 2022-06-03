package com.scando.learning.common.utils;

import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.common.dao.ApplicationDao;
import com.scando.learning.common.models.rest.NotificationPublishRequest;
import com.scando.learning.common.models.rest.NotificationSendRequest;
import com.scando.learning.common.service.WebFluxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class NotificationUtils {

    @Autowired
    ApplicationDao applicationDao;

    @Autowired
    WebFluxService webFluxService;

    private List<String> getFcmTokens(Long userId) {
        return applicationDao.getFcmToken(userId);
    }

    public void triggerNotification(Long userId , String message , String title) {
        NotificationPublishRequest notificationPublishRequest = NotificationPublishRequest.builder()
                .topic(userId.toString())
                .message(message)
                .title(title)
                .build();

        List<String> fcmTokens = getFcmTokens(userId);

        NotificationSendRequest notificationSendRequest = NotificationSendRequest.builder()
                .tokens(fcmTokens)
                .message(message)
                .title(title)
                .build();

        webFluxService.post(notificationPublishRequest, ApiUrls.PUBLISH_NOTIFICATION);
        webFluxService.post(notificationSendRequest, ApiUrls.SEND_NOTIFICATION);
    }
}
