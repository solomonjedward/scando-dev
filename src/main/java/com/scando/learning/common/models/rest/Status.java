package com.scando.learning.common.models.rest;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.scando.learning.common.constants.ApiUrls;
import com.scando.learning.common.utils.WebUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.io.Serializable;

@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status implements Serializable {

    @ApiModelProperty(example = "20001", position = 1)
    private Integer statusCode;

    @ApiModelProperty(example = "1025454301", position = 3)
    private Long startTime;

    @ApiModelProperty(example = "1025459301", position = 4)
    private Long endTime;

    @ApiModelProperty(example = "1", position = 5)
    private Integer apiId;

    public Status(Integer statusCode, String statusMessage) {
        this.statusCode = statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
        if (!WebUtils.isDebug())
            this.startTime = null;

    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
        if (!WebUtils.isDebug())
            this.endTime = null;

    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
        if (!WebUtils.isDebug())
            this.apiId = null;
    }
}
