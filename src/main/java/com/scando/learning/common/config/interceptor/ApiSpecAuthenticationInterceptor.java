package com.scando.learning.common.config.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class ApiSpecAuthenticationInterceptor implements AsyncHandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
    	HttpSession session = request.getSession();    	
    	if(session == null || session.getAttribute("API_SPEC_AUTH_USER") == null) {
    		response.sendRedirect("/api-spec-login");
    		return false;
    	}    	
        return true;
    }
}
