package com.rige.dtos.request;

import lombok.Data;

import java.util.List;

@Data
public class ScheduleRequest {
    private Long userId;
    List<Long> courseIds;
}
