package com.rige.services.impl;

import com.rige.dtos.request.ScheduleRequest;
import com.rige.entities.AssignmentEntity;
import com.rige.entities.ScheduleDetailEntity;
import com.rige.entities.ScheduleEntity;
import com.rige.entities.StudentEntity;
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
        String courseIdList = String.join(",", request.getCourseIds().stream().
                map(String::valueOf).toArray(String[]::new));

        List<Long> assignmentList = assignmentRepository.findAssignmentsByCourseIdList(courseIdList);

        StudentEntity student = StudentEntity.builder().id(request.getUserId()).build();

        ScheduleEntity schedule = scheduleRepository.save(ScheduleEntity.builder().student(student).build());

        List<ScheduleDetailEntity> scheduleDetailList = new ArrayList<>();

        for (Long assignmentId : assignmentList) {
            ScheduleDetailEntity scheduleDetail = ScheduleDetailEntity.builder()
                    .assignment(AssignmentEntity.builder()
                            .id(assignmentId).build()).schedule(schedule).build();
            scheduleDetailList.add(scheduleDetail);
        }

        scheduleDetailRepository.saveAll(scheduleDetailList);
    }
}
