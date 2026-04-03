package com.example.coursemanagementsystem.service;

import com.example.coursemanagementsystem.dto.*;
import com.example.coursemanagementsystem.models.Course;
import com.example.coursemanagementsystem.models.Instructor;
import com.example.coursemanagementsystem.repository.CourseRepository;
import com.example.coursemanagementsystem.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired private CourseRepository courseRepository;
    @Autowired
    private InstructorRepository instructorRepository;

    public PageResponse<CourseResponse> getPagedCourses(int page, int size, String sortBy, Sort.Direction direction) {
        // Chuẩn hóa: Nếu Frontend gửi page = 1 thì hiểu là trang đầu tiên
        int validatedPage = Math.max(page, 1);
        String validatedSortBy = (sortBy == null || sortBy.isEmpty()) ? "id" : sortBy;

        // PageRequest của Spring tính từ index 0
        Pageable pageable = PageRequest.of(validatedPage - 1, size, Sort.by(direction, validatedSortBy));

        // Lấy dữ liệu từ Repository
        Page<Course> coursePage = courseRepository.findAll(pageable);

        // Sử dụng phương thức map() của Page để chuyển đổi Entity -> DTO
        Page<CourseResponse> responsePage = coursePage.map(course -> CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .status(course.getStatus())
                .instructorName(course.getInstructor() != null ? course.getInstructor().getName() : "N/A")
                .build());

        // Đóng gói vào PageResponse
        return PageResponse.<CourseResponse>builder()
                .items(responsePage.getContent())
                .page(validatedPage) // Trả về page thực tế mà User nhìn thấy (bắt đầu từ 1)
                .size(responsePage.getSize())
                .totalItems(responsePage.getTotalElements())
                .totalPages(responsePage.getTotalPages())
                .isLast(responsePage.isLast())
                .build();
    }
    public PageResponse<CourseProjection> getPagedCoursesUsingProjection(int page, int size, String sortBy, Sort.Direction direction) {
        int validatedPage = Math.max(page, 1);
        String validatedSortBy = (sortBy == null || sortBy.isEmpty()) ? "id" : sortBy;

        Pageable pageable = PageRequest.of(validatedPage - 1, size, Sort.by(direction, validatedSortBy));

        // Dữ liệu từ Repo đã là Projection rồi, không cần .map() thủ công nữa
        Page<CourseProjection> projectionPage = courseRepository.findAllProjectedBy(pageable);

        return PageResponse.<CourseProjection>builder()
                .items(projectionPage.getContent())
                .page(validatedPage)
                .size(projectionPage.getSize())
                .totalItems(projectionPage.getTotalElements())
                .totalPages(projectionPage.getTotalPages())
                .isLast(projectionPage.isLast())
                .build();
    }

    public void createCourse(CourseCreateRequest req) {
        Instructor instructor = instructorRepository.findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        Course course = new Course();
        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        courseRepository.save(course);
    }

    public void updateCourse(Long id, CourseUpdateRequest req) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Instructor instructor = instructorRepository.findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        courseRepository.save(course);
    }
}
