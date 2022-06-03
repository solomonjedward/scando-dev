package com.scando.learning.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VerifyType {

    OTP_VERIFICATION(1 , "otp verification"),
    USER_LOGIN(0 , "login");

    public final int code;
    public final String desc;
}
