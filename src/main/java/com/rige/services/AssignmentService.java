package com.rige.services;

import com.rige.dtos.response.CareerResponse;

public interface AssignmentService {
    CareerResponse getAssignmentsByCareer(Integer careerId);
}
