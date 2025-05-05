package com.rige.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licenseNumber;
    private Integer maxHours;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToMany
    @JoinTable(
            name = "specialty_teacher",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Set<SpecialtyEntity> specialties;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;
}
