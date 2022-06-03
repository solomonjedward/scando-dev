package com.scando.learning.common.config.interceptor;

import com.scando.learning.common.models.rest.Status;
import com.scando.learning.common.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class CommonIntercepter implements AsyncHandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       if(request.getRequestURI().contains("api/")) {
           LOGGER.debug(request.getRequestURI());
       }
        WebUtils.setIsDebug(Boolean.valueOf(request.getHeader("debug")));
        Status status = new Status();
        status.setStartTime(System.currentTimeMillis());
        WebUtils.setStatus(status);
        return true;
    }
}
