package com.example.coursemanagementsystem.service;

import com.example.coursemanagementsystem.dto.InstructorCreateRequest;
import com.example.coursemanagementsystem.models.Instructor;
import com.example.coursemanagementsystem.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    // Tìm giảng viên theo ID
    public Instructor findInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên với ID: " + id));
    }

    // Lấy danh sách tất cả giảng viên
    public List<Instructor> findAllInstructors() {
        return instructorRepository.findAll();
    }

    // Tạo mới giảng viên từ request DTO
    public Instructor createInstructor(InstructorCreateRequest req) {
        Instructor instructor = new Instructor();
        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());

        // Lưu vào database thông qua repository
        return instructorRepository.save(instructor);
    }
}