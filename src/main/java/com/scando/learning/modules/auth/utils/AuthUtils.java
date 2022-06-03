package com.scando.learning.modules.auth.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.scando.learning.common.constants.UserType;
import com.scando.learning.common.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class AuthUtils {

    @Value("${spring.jwt.secret}")
    private String secret;


    public HashMap<String, String> getJwtToken(String mobileNumber, UserType userType, User user) {
        Long authExpiry = System.currentTimeMillis() + (24 * 60 * 60 * 1000);
        Long refreshExpiry = System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000);
        String token = generateJWT(mobileNumber, userType.role, user, "auth", authExpiry);
        String refreshToken = generateJWT(mobileNumber, userType.role, user, "refresh", refreshExpiry);
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("refreshToken", refreshToken);
        tokenMap.put("expiry", authExpiry.toString());
        return tokenMap;
    }

    private String generateJWT(String mobileNumber, String role, User user, String type, Long expiry) {
        Algorithm algorithm = Algorithm.HMAC512(secret + "-" + type);
        String jws = JWT.create()
                .withIssuer("Scando")
                .withClaim("phoneNumber", user.getPhoneNumber())
                .withClaim("role", role)
                .withClaim("userStatus", user.getUserStatus())
                .withExpiresAt(new Date(expiry))
                .sign(algorithm);
        return jws;

    }

}
