package com.scando.learning.common.repository;

import com.scando.learning.common.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User , Long> {

    User getByPhoneNumber(String mobileNumber);

    User getByUserId(Long userId);

}
