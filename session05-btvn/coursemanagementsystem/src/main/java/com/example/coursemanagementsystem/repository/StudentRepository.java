package com.example.coursemanagementsystem.repository;

import com.example.coursemanagementsystem.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}
