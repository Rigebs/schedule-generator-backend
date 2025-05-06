package com.rige.dtos.response;

import lombok.Data;

import java.util.List;

@Data
public class CourseResponse {
    private Long assignmentId;
    private String courseId;
    private String course;
    private Integer credits;
    private List<CourseDetailResponse> details;
}
