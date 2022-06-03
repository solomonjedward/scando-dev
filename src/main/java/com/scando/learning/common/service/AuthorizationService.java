package com.scando.learning.common.service;


import com.scando.learning.common.models.UserLoginInfo;

public interface AuthorizationService {

    UserLoginInfo getUserLoginDetails(String token);
}
