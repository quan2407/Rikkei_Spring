package com.example.coursemanagementsystem.dto;

import com.example.coursemanagementsystem.CourseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseCreateRequest {
    private String title;
    private CourseStatus status;
    private Long instructorId;
}