package com.rige.services;

import com.rige.dtos.request.GenerateSchedulesRequest;
import com.rige.dtos.response.CareerResponse;

public interface ScheduleService {
    CareerResponse generateSchedules(GenerateSchedulesRequest request);
}
