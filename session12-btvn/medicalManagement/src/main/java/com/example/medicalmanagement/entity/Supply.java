package com.example.medicalmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "supplies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String provider;
    private String specification;
    private String unit;

    @Builder.Default
    private Integer quantity = 0;

    @Builder.Default
    private Boolean isDeleted = false;
}