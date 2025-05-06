package com.rige.dtos.response;

import lombok.Data;

import java.util.List;

@Data
public class CycleResponse {
    private String cycle;
    private List<CourseResponse> courses;
}
