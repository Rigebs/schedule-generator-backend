package com.rige.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "preferences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PreferenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;
    private Integer weight;

    @ManyToOne
    @JoinColumn(name = "preference_type_id")
    private PreferenceTypeEntity preferenceType;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;
}
