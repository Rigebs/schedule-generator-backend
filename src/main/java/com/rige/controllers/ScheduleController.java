package com.rige.controllers;

import com.rige.dtos.request.ScheduleRequest;
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
    public void save(@RequestBody ScheduleRequest scheduleRequest) {
        scheduleService.save(scheduleRequest);
    }
}
