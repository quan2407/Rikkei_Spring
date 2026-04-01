package com.example.coursemanagementsystem.repository;

import com.example.coursemanagementsystem.models.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InstructorRepository extends JpaRepository<Instructor, Long> {}
