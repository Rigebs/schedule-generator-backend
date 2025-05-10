package com.rige.services.impl;

import com.rige.dtos.request.ScheduleRequest;
import com.rige.entities.*;
import com.rige.repositories.AssignmentRepository;
import com.rige.repositories.ScheduleDetailRepository;
import com.rige.repositories.ScheduleRepository;
import com.rige.services.ScheduleService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleDetailRepository scheduleDetailRepository;
    private final AssignmentRepository assignmentRepository;


    @Override
    public void save(ScheduleRequest request) {
        //TODO: implement this method
    }
}
