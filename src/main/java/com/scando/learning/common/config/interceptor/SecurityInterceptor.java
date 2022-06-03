package com.scando.learning.common.config.interceptor;

import com.scando.learning.common.constants.ErrorCodeEnum;
import com.scando.learning.common.exception.ServiceException;
import com.scando.learning.common.models.UserLoginInfo;
import com.scando.learning.common.models.rest.Status;
import com.scando.learning.common.service.AuthorizationService;
import com.scando.learning.common.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecurityInterceptor  implements AsyncHandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityInterceptor.class);
    private static final String SECURITY_TOKEN_HEADER_NAME = "Authorization";

    @Autowired
    private AuthorizationService authorizationService;

    public SecurityInterceptor() {
        LOGGER.debug("Security Interceptor Init");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
       try{
           /* cors issue solution */
           if(request.getMethod().equals("OPTIONS"))
               return true;
           /*----------------------------*/
           final String token = WebUtils.getHeader(SECURITY_TOKEN_HEADER_NAME).split(" ")[1];
           final UserLoginInfo userLoginInfo = authorizationService.getUserLoginDetails(token);
           if(null ==userLoginInfo) {
               throw new ServiceException( ErrorCodeEnum.UNAUTHORIZED);
           }
           WebUtils.setUserId(userLoginInfo.getUserId());
           LOGGER.debug("Login userId : {} , appId : {}",WebUtils.getUserId() , userLoginInfo.getAppId());
           return true;
       } catch (Exception e) {
           throw new ServiceException(ErrorCodeEnum.UNAUTHORIZED);
       }
    }
}

