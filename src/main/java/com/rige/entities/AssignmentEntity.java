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

    @ManyToOne
    @JoinColumn(name = "career_id")
    private CareerEntity career;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;
}