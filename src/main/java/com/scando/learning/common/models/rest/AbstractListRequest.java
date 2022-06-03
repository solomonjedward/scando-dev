package com.scando.learning.common.models.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public abstract class AbstractListRequest {

    private static final long serialVersionUID = 1L;

    private static final Integer PAGE_DEFAULT_VALUE = 1;

    private static final Integer PAGE_SIZE_DEFAULT_VALUE = 20;

    @Min(1)
    @Max(Integer.MAX_VALUE)
    @ApiModelProperty(value = "Page number", notes = "Start from 1. default : 1")
    private Integer page = PAGE_DEFAULT_VALUE;

    @Min(1)
    @Max(Integer.MAX_VALUE)
    @ApiModelProperty(value = "Rows per page", notes = "default : 20")
    private Integer limit = PAGE_SIZE_DEFAULT_VALUE;

    public void setPage(Integer page) {
        if(page == null) page = PAGE_DEFAULT_VALUE;
        this.page = page;
    }

    public void setPageSize(Integer limit) {
        if(limit == null) limit = PAGE_SIZE_DEFAULT_VALUE;
        this.limit = limit;
    }
}
