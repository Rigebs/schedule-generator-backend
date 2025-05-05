package com.rige.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "preference_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PreferenceTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}

