package com.rige.dtos.response;

import lombok.Data;

import java.sql.Time;

@Data
public class ClassTypeResponse {
    private Long assignmentDetailId;
    private String classroom;
    private String classType;
    private String day;
    private Time startTime;
    private Time endTime;
    private Boolean value;
    private String message;
}
