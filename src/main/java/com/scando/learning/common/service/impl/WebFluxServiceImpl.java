package com.scando.learning.common.service.impl;

import com.scando.learning.common.models.rest.NotificationResponse;
import com.scando.learning.common.service.WebFluxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class WebFluxServiceImpl<T> implements WebFluxService<T> {

    @Value("${scando.server.notification}")
    private String notificationServer;

    @Override
    public void post(T payload, String uri) {
        WebClient webClient = WebClient.builder().baseUrl(notificationServer).build();
        NotificationResponse responseMono = webClient.post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(payload), payload.getClass())
                .retrieve()
                .bodyToMono(NotificationResponse.class)
                .block();

        if(null == responseMono) {
            LOGGER.debug("Notification send failed");
        } else {
            LOGGER.debug( "api response : {} " ,responseMono.getResponse());
        }
    }
}
