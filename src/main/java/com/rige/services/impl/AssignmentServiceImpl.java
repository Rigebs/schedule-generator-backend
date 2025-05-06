package com.rige.services.impl;

import com.rige.dtos.response.CareerResponse;
import com.rige.dtos.response.CourseDetailResponse;
import com.rige.dtos.response.CycleResponse;
import com.rige.dtos.response.CourseResponse;
import com.rige.projections.CourseAssignmentProjection;
import com.rige.repositories.AssignmentRepository;
import com.rige.services.AssignmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    @Override
    public CareerResponse getAssignmentsByCareer(Integer careerId) {

        List<CourseAssignmentProjection> flatList = assignmentRepository.findAssignmentsByCareerId(careerId);

        CareerResponse response = new CareerResponse();
        if (flatList.isEmpty()) return response;

        response.setCareer(flatList.get(0).getCareer());

        Map<String, Map<String, CourseResponse>> cycleCourseMap = new LinkedHashMap<>();

        for (CourseAssignmentProjection dto : flatList) {
            Map<String, CourseResponse> courseMap =
                    cycleCourseMap.computeIfAbsent(dto.getCycle(), k -> new LinkedHashMap<>());

            CourseResponse course = courseMap.get(dto.getCourseName());

            if (course == null) {
                course = new CourseResponse();
                course.setAssignmentId(dto.getAssignmentId());
                course.setCourseId(dto.getCourseId());
                course.setCourse(dto.getCourseName());
                course.setCredits(dto.getCredits());
                course.setDetails(new ArrayList<>());
                courseMap.put(dto.getCourseName(), course);
            }

            CourseDetailResponse detail = new CourseDetailResponse();
            detail.setClassroom(dto.getClassroomName());
            detail.setDay(dto.getDay());
            detail.setStartTime(dto.getStartTime());
            detail.setEndTime(dto.getEndTime());
            detail.setTeacher(dto.getProfessorName());

            course.getDetails().add(detail);
        }

        List<CycleResponse> cycles = new ArrayList<>();
        for (Map.Entry<String, Map<String, CourseResponse>> cycleEntry : cycleCourseMap.entrySet()) {
            CycleResponse cycle = new CycleResponse();
            cycle.setCycle(cycleEntry.getKey());

            List<CourseResponse> courses = new ArrayList<>(cycleEntry.getValue().values());
            cycle.setCourses(courses);
            cycles.add(cycle);
        }

        response.setCycles(cycles);
        return response;
    }

}
