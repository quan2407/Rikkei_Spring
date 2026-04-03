package com.example.coursemanagementsystem.repository;

import com.example.coursemanagementsystem.dto.CourseProjection;
import com.example.coursemanagementsystem.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<CourseProjection> findAllProjectedBy(Pageable pageable);
}
