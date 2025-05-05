package com.rige.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "configurations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfigurationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "min_time")
    private LocalTime minTime;

    @Column(name = "max_time")
    private LocalTime maxTime;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private SchoolEntity school;
}