package com.example.coursemanagementsystem.dto;


import org.springframework.beans.factory.annotation.Value;

public interface CourseProjection {
    Long getId();
    String getTitle();
    String getStatus();

    // Sử dụng @Value (SpEL) để lấy name từ entity Instructor liên kết
    @Value("#{target.instructor.name}")
    String getInstructorName();
}
