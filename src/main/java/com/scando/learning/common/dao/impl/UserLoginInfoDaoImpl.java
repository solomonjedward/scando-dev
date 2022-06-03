package com.scando.learning.common.dao.impl;

import com.scando.learning.common.dao.UserLoginInfoDao;
import com.scando.learning.common.models.UserLoginInfo;
import com.scando.learning.common.repository.UserLoginInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserLoginInfoDaoImpl implements UserLoginInfoDao {

    @Autowired
    UserLoginInfoRepository userLoginInfoRepository;

    @Override
    public UserLoginInfo save(UserLoginInfo userLoginInfo) {
        return userLoginInfoRepository.save(userLoginInfo);
    }

    @Override
    public void delete(UserLoginInfo userLoginInfo) {
        userLoginInfoRepository.delete(userLoginInfo);
    }

    @Override
    public UserLoginInfo getUserLoginInfo(Long userLoginId) {
        return userLoginInfoRepository.getByUserLoginId(userLoginId);
    }

    @Override
    public UserLoginInfo getUserLoginInfoByAppIdandUserId(Long appId, Long userId) {
        return userLoginInfoRepository.getByAppIdAndUserId(appId, userId);
    }

    @Override
    public UserLoginInfo getByToken(String token) {
        return userLoginInfoRepository.getByToken(token);
    }
}
