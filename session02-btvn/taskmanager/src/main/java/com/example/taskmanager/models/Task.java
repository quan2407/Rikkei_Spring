package com.example.taskmanager.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String title;
    private String description;
    private String priority; // "high", "medium", "low"
    private Long assignedTo; // Chứa ID của User
    // Constructor, Getter, Setter
}