package com.rige.dtos.http.response;

import lombok.Data;

import java.sql.Time;

@Data
public class AssignmentDetailResponse {
    private Long assignmentDetailId;
    private String career;
    private String cycle;
    private String classType;
    private Long courseId;
    private String courseName;
    private Integer credits;
    private String professorName;
    private String classroomName;
    private String day;
    private Time startTime;
    private Time endTime;
    private Boolean value;
    private String message;
}
