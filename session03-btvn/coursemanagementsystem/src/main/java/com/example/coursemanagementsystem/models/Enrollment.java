package com.example.coursemanagementsystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Enrollment {
    private Long id;
    private String studentName;
    private Long courseId;
    // Constructor, Getter, Setter
}
