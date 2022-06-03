package com.scando.learning.common.models.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PagedData<T> {

    private static final long serialVersionUID = 1L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty("Pagination details")
    private PageDetails pageDetails;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(position = 1, value = "Array containing items")
    private List<T> list = new ArrayList<>();
}
