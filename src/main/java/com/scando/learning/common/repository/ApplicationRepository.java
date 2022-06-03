package com.scando.learning.common.repository;


import com.scando.learning.common.models.Application;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Application getByAppId(Long appId);

    Application getByDeviceId(String deviceId);

    @Query("select new java.lang.String(app.deviceToken) "
            + "from UserLoginInfo usr "
            + "join Application app on app.appId = usr.appId "
            + "where usr.userId = :userId")
    List<String> getFcmTokens(@Param("userId") Long userId);
}
