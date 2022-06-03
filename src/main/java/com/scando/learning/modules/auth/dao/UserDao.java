package com.scando.learning.modules.auth.dao;

import com.scando.learning.common.dao.AbstractDao;
import com.scando.learning.common.models.Otp;
import com.scando.learning.common.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends AbstractDao<User> {

    User getUserByNumber(String mobileNumber);

    User getUser(Long userId);

    Otp saveGeneratedOtp(Otp otpData);

    void deleteGeneratedOtp(Otp otpData);

    Otp getExistingOtp(String phoneNumber);

    Otp getByMobileNumberAndGeneratedOtp(String phoneNumber, int otp);
}
