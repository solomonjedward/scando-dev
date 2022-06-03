package com.scando.learning.common.models.rest;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSendRequest {

    @NotNull(message = "tokens cannot be null")
    private List<String> tokens;

    @NotNull(message = "title cannot be null")
    private String title;

    @NotNull(message = "message cannot be null")
    private String message;
}
