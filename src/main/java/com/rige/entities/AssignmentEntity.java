package com.rige.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "assignments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cycle;
    private String shift;
    private Integer credit;
    private Integer hoursPerWeek;
    private Boolean isReady;

    @ManyToOne
    @JoinColumn(name = "career_id")
    private CareerEntity career;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private AssignmentEntity assignment;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;
}