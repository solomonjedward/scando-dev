package com.scando.learning.common.repository;

import com.scando.learning.common.models.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    Otp getByPhoneNumber(String phoneNumber);

    Otp getByPhoneNumberAndOtpGenerated(String phoneNumber , int otp);
}
