package com.rige.controllers;

import com.rige.dtos.response.CareerResponse;
import com.rige.services.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {
    private final AssignmentService assignmentService;

    @GetMapping("/{careerId}")
    public CareerResponse getAssignments(@PathVariable Integer careerId) {
        return assignmentService.getAssignmentsByCareer(careerId);
    }
}
