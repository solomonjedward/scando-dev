package com.scando.learning.modules.auth.dao.impl;

import com.scando.learning.common.models.Otp;
import com.scando.learning.common.repository.OtpRepository;
import com.scando.learning.modules.auth.dao.UserDao;
import com.scando.learning.common.models.User;
import com.scando.learning.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserImpl implements UserDao {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OtpRepository otpRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User getUserByNumber(String mobileNumber) {
        return userRepository.getByPhoneNumber(mobileNumber);
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.getByUserId(userId);
    }

    @Override
    public Otp saveGeneratedOtp(Otp otpData) {
        return otpRepository.save(otpData);
    }

    @Override
    public void deleteGeneratedOtp(Otp otpData) {
         otpRepository.delete(otpData);
    }

    @Override
    public Otp getExistingOtp(String phoneNumber) {
        return otpRepository.getByPhoneNumber(phoneNumber);
    }

    @Override
    public Otp getByMobileNumberAndGeneratedOtp(String phoneNumber, int otp) {
        return otpRepository.getByPhoneNumberAndOtpGenerated(phoneNumber, otp);
    }

}



