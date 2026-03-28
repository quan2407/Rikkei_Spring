package com.example.coursemanagementsystem.repository;

import com.example.coursemanagementsystem.models.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CourseRepository {
    private List<Course> courses = new ArrayList<>(Arrays.asList(
            new Course(1L, "Java Spring Boot", "ACTIVE", 1L),
            new Course(2L, "Korean for Engineers", "ACTIVE", 2L)
    ));

    public List<Course> findAll() { return courses; }
}
