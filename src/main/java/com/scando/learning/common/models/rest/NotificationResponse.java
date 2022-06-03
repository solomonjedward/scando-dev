package com.scando.learning.common.models.rest;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponse implements Serializable {

    private String response;

}
