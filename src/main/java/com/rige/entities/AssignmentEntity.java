package com.rige.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "assignments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cycle;
    private String shift;
    private Integer credits;
    private Integer hoursPerWeek;
    private Boolean isReady;

    @ManyToOne
    @JoinColumn(name = "career_id")
    private CareerEntity career;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassroomEntity classroom;

    @ManyToOne
    @JoinColumn(name = "timeslot_id")
    private TimeslotEntity timeslot;
}