package com.scando.learning.common.utils;

import com.scando.learning.common.models.rest.Status;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public final class WebUtils {
    private static final String RESPONSE_STATUS = "Status";
    private static final String IS_DEBUG = "isDebug";
    public static final String USER_ID = "userId";

    public WebUtils() {

    }

    public static String getHeader(String headerName) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)) {
            return null;
        }
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest().getHeader(headerName);
    }

    public static void setStatus(Status status) {
        RequestContextHolder.getRequestAttributes().setAttribute(RESPONSE_STATUS, status,
                RequestAttributes.SCOPE_REQUEST);
    }

    public static Status getStatus() {
        return (Status) RequestContextHolder.getRequestAttributes().getAttribute(RESPONSE_STATUS,
                RequestAttributes.SCOPE_REQUEST);
    }

    public static void setIsDebug(Boolean isDebug) {
        RequestContextHolder.getRequestAttributes().setAttribute(IS_DEBUG, isDebug, RequestAttributes.SCOPE_REQUEST);
    }

    public static Boolean isDebug() {
        return (Boolean) RequestContextHolder.getRequestAttributes().getAttribute(IS_DEBUG,
                RequestAttributes.SCOPE_REQUEST);
    }

    public static void setUserId(Long userId) {
        RequestContextHolder.getRequestAttributes().setAttribute(USER_ID, userId, RequestAttributes.SCOPE_REQUEST);
    }

    public static Long getUserId() {
        return (Long) RequestContextHolder.getRequestAttributes().getAttribute(USER_ID,
                RequestAttributes.SCOPE_REQUEST);
    }
}
