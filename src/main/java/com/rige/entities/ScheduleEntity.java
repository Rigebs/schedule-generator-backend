package com.rige.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;
}