package com.rige.controllers;

import com.rige.dtos.request.GenerateSchedulesRequest;
import com.rige.dtos.response.ApiResponse;
import com.rige.dtos.response.CareerResponse;
import com.rige.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ApiResponse<CareerResponse> generate(@RequestBody GenerateSchedulesRequest request) {
        return ApiResponse.<CareerResponse>builder()
                .data(scheduleService.generateSchedules(request))
                .message("Schedules generated successfully")
                .success(true)
                .build();
    }
}
