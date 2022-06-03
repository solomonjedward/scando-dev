package com.scando.learning.common.models.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class NotificationPublishRequest implements Serializable {

    @NotNull(message = "topic cannot be null")
    private String topic;

    @NotNull(message = "title cannot be null")
    private String title;

    @NotNull(message = "message cannot be null")
    private String message;

}