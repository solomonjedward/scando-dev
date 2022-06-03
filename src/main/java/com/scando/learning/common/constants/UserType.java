package com.scando.learning.common.constants;

import lombok.Getter;

@Getter
public enum UserType {

    TEACHER(0, "Teacher"),
    STUDENT(1, "Student");

    public Integer code;
    public String role;

    UserType(Integer code, String role) {
        this.code = code;
        this.role = role;
    }

    public static UserType getUserType(Integer code) {
        if (code == 0)
            return UserType.TEACHER;
        else
            return UserType.STUDENT;
    }

}
