package com.example.coursemanagementsystem.dto;

import com.example.coursemanagementsystem.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private Long id;
    private String title;
    private CourseStatus status;
    private String instructorName; // Ví dụ thêm tên giảng viên
}