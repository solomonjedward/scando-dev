package com.scando.learning.common.constants;

import lombok.Getter;

@Getter
public enum ScheduleDayType {

    MON(1, "01" ,"Mon"),
    TUE(2, "02" ,"Tue"),
    WED(3, "03" ,"Wed"),
    THU(4, "04" ,"Thu"),
    FRI(5, "05" ,"Fri"),
    SAT(6, "06" ,"Sat"),
    SUN(7, "07" ,"Sun");

    private int index;

    private String code;

    private String description;
    ScheduleDayType(int index, String code , String description) {
        this.index = index;
        this.code = code;
        this.description = description;
    }
}
