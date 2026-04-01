package com.example.coursemanagementsystem.repository;

import com.example.coursemanagementsystem.models.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {}
