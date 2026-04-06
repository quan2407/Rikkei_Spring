package com.example.recruit.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "candidates")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private Integer age;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;
    private String address;

    @Column(columnDefinition = "TEXT")
    private String bio;
    @Column(name = "phone", length = 10)
    private String phone;
}