package com.scando.learning.common.dao;


import com.scando.learning.common.models.UserLoginInfo;

public interface UserLoginInfoDao extends AbstractDao<UserLoginInfo> {


    UserLoginInfo getUserLoginInfo(Long userLoginId);

    UserLoginInfo getUserLoginInfoByAppIdandUserId(Long appId, Long userId);

    UserLoginInfo getByToken(String token);
}
