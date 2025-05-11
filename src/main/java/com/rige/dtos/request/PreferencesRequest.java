package com.rige.dtos.request;

import lombok.Data;

import java.util.List;

@Data
public class PreferencesRequest {
    private List<String> avoidDays;
    private String avoidStartHour;
    private List<String> preferredTeachers;
    private List<String> preferredModalities;
    private Integer maxHoursPerDay;
    private Integer minDaysPerWeek;
    private Integer maxDaysPerWeek;
    private List<BlockedHourRequest> blockedHours;
}
