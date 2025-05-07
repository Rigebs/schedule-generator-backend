package com.rige.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "schedule_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private AssignmentEntity assignment;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private ScheduleEntity schedule;
}