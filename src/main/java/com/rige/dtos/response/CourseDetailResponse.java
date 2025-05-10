package com.rige.dtos.response;

import lombok.Data;
import java.util.List;

@Data
public class CourseDetailResponse {
    private String teacher;
    private List<ClassTypeResponse> classTypes;
}
