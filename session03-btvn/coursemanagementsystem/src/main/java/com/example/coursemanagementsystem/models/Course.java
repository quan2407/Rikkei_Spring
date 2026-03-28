package com.example.coursemanagementsystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Course {
    private Long id;
    private String title;
    private String status; // "ACTIVE", "INACTIVE"
    private Long instructorId;
    // Constructor, Getter, Setter
}