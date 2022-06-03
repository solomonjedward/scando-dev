package com.scando.learning.common.repository;

import com.scando.learning.common.models.UserLoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginInfoRepository extends JpaRepository<UserLoginInfo, Long> {

    UserLoginInfo getByUserLoginId(Long userLoginId);

    UserLoginInfo getByAppIdAndUserId(Long appId, Long userId);

    UserLoginInfo getByToken(String token);
}
