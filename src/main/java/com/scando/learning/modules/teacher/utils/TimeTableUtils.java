package com.scando.learning.modules.teacher.utils;

import com.scando.learning.modules.teacher.models.ScheduledClass;
import com.scando.learning.modules.teacher.models.rest.Time;
import com.scando.learning.modules.teacher.models.rest.TimeTableSession;

import java.util.ArrayList;
import java.util.List;

public class TimeTableUtils {

    public static List<TimeTableSession> formatTimeTable(List<ScheduledClass> scheduledClasses) {
        List<TimeTableSession> timeTableSessions = new ArrayList<>();
        for (ScheduledClass scheduledClass : scheduledClasses) {
            timeTableSessions.add(TimeTableSession.builder()
                    .day(scheduledClass.getDay())
                    .start(Time.builder()
                            .hour(scheduledClass.getStartHour())
                            .min(scheduledClass.getStartMin())
                            .build())
                    .end(Time.builder()
                            .hour(scheduledClass.getEndHour())
                            .min(scheduledClass.getEndMin())
                            .build())
                    .build());

        }
        return timeTableSessions;
    }
}
