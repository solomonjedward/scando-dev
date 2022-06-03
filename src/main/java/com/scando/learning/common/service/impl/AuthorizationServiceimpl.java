package com.scando.learning.common.service.impl;

import com.scando.learning.common.dao.UserLoginInfoDao;
import com.scando.learning.common.models.UserLoginInfo;
import com.scando.learning.common.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceimpl implements AuthorizationService {

    @Autowired
    UserLoginInfoDao userLoginInfoDao;

    @Override
    public UserLoginInfo getUserLoginDetails(String token) {
        return userLoginInfoDao.getByToken(token);
    }
}
