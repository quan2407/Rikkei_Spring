package com.example.coursemanagementsystem.service;

import com.example.coursemanagementsystem.models.Course;
import com.example.coursemanagementsystem.models.Enrollment;
import com.example.coursemanagementsystem.models.Instructor;
import com.example.coursemanagementsystem.repository.CourseRepository;
import com.example.coursemanagementsystem.repository.EnrollmentRepository;
import com.example.coursemanagementsystem.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademyService {
    private final InstructorRepository instructorRepo;
    private final CourseRepository courseRepo;
    private final EnrollmentRepository enrollmentRepo;

    // Constructor Injection (Bắt buộc theo yêu cầu)
    @Autowired
    public AcademyService(InstructorRepository instructorRepo,
                          CourseRepository courseRepo,
                          EnrollmentRepository enrollmentRepo) {
        this.instructorRepo = instructorRepo;
        this.courseRepo = courseRepo;
        this.enrollmentRepo = enrollmentRepo;
    }

    public List<Instructor> getAllInstructors() { return instructorRepo.findAll(); }
    public List<Course> getAllCourses() { return courseRepo.findAll(); }
    public List<Enrollment> getAllEnrollments() { return enrollmentRepo.findAll(); }
}
