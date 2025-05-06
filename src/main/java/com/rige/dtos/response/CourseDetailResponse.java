package com.rige.dtos.response;

import lombok.Data;

import java.sql.Time;

@Data
public class CourseDetailResponse {
    private String classroom;
    private String day;
    private Time startTime;
    private Time endTime;
    private String teacher;
}
