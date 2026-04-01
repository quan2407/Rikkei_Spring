package com.example.coursemanagementsystem.controller;

import com.example.coursemanagementsystem.dto.ApiResponse;
import com.example.coursemanagementsystem.dto.CourseCreateRequest;
import com.example.coursemanagementsystem.dto.CourseUpdateRequest;
import com.example.coursemanagementsystem.dto.InstructorCreateRequest;
import com.example.coursemanagementsystem.models.Student;
import com.example.coursemanagementsystem.repository.StudentRepository;
import com.example.coursemanagementsystem.service.CourseService;
import com.example.coursemanagementsystem.service.InstructorService;
import com.example.coursemanagementsystem.service.StudentEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    private InstructorService instructorService;
    @Autowired private CourseService courseService;
    @Autowired private StudentEnrollmentService enrollmentService;
    @Autowired private StudentRepository studentRepository; // Giả định dùng Repo nhanh cho Student

    // POST /instructors
    @PostMapping("/instructors")
    public ResponseEntity<ApiResponse<Void>> createInstructor(@RequestBody InstructorCreateRequest req) {
        instructorService.createInstructor(req);
        return ResponseEntity.ok(new ApiResponse<>("Tạo giảng viên thành công", null));
    }

    // POST /courses
    @PostMapping("/courses")
    public ResponseEntity<ApiResponse<Void>> createCourse(@RequestBody CourseCreateRequest req) {
        courseService.createCourse(req);
        return ResponseEntity.ok(new ApiResponse<>("Tạo khóa học thành công", null));
    }

    // PUT /courses/{id}
    @PutMapping("/courses/{id}")
    public ResponseEntity<ApiResponse<Void>> updateCourse(@PathVariable Long id, @RequestBody CourseUpdateRequest req) {
        courseService.updateCourse(id, req);
        return ResponseEntity.ok(new ApiResponse<>("Cập nhật khóa học thành công", null));
    }

    // POST /students (Giả định tạo nhanh student)
    @PostMapping("/students")
    public ResponseEntity<ApiResponse<Void>> createStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return ResponseEntity.ok(new ApiResponse<>("Tạo sinh viên thành công", null));
    }

    // POST /students-enrollments
    @PostMapping("/students-enrollments")
    public ResponseEntity<ApiResponse<Void>> enroll(@RequestParam Long studentId, @RequestParam Long courseId) {
        enrollmentService.enrollStudent(studentId, courseId);
        return ResponseEntity.ok(new ApiResponse<>("Đăng ký môn học thành công", null));
    }
}
