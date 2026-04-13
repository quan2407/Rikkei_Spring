package com.example.departmentmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phone;
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    private String avatarUrl;
}