package com.example.coursemanagementsystem.repository;

import com.example.coursemanagementsystem.models.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class EnrollmentRepository {
    private List<Enrollment> enrollments = new ArrayList<>(Arrays.asList(
            new Enrollment(1L, "Quan Nguyen", 1L),
            new Enrollment(2L, "Minh Tran", 1L)
    ));

    public List<Enrollment> findAll() { return enrollments; }
}
