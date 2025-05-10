package com.rige.services.impl;

import com.rige.dtos.response.*;
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
                course.setCourseId(dto.getCourseId());
                course.setCourse(dto.getCourseName());
                course.setCredits(dto.getCredits());
                course.setDetails(new ArrayList<>());
                courseMap.put(dto.getCourseName(), course);
            }

            // 🔄 Agrupar solo por profesor
            CourseDetailResponse existingDetail = course.getDetails().stream()
                    .filter(d -> d.getTeacher().equals(dto.getProfessorName()))
                    .findFirst()
                    .orElse(null);

            if (existingDetail == null) {
                existingDetail = new CourseDetailResponse();
                existingDetail.setTeacher(dto.getProfessorName());
                existingDetail.setClassTypes(new ArrayList<>());
                course.getDetails().add(existingDetail);
            }

            // Ahora classroom va dentro de classType
            ClassTypeResponse classType = new ClassTypeResponse();
            classType.setAssignmentDetailId(dto.getAssignmentDetailId());
            classType.setClassroom(dto.getClassroomName());
            classType.setClassType(dto.getClassType());
            classType.setDay(dto.getDay());
            classType.setStartTime(dto.getStartTime());
            classType.setEndTime(dto.getEndTime());

            existingDetail.getClassTypes().add(classType);
        }

        List<CycleResponse> cycles = new ArrayList<>();
        for (Map.Entry<String, Map<String, CourseResponse>> cycleEntry : cycleCourseMap.entrySet()) {
            CycleResponse cycle = new CycleResponse();
            cycle.setCycle(cycleEntry.getKey());
            cycle.setCourses(new ArrayList<>(cycleEntry.getValue().values()));
            cycles.add(cycle);
        }

        response.setCycles(cycles);
        return response;
    }

}
