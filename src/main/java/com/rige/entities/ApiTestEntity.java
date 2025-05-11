package com.rige.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "api_test")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApiTestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
}
