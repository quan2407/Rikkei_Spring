package com.example.demo.controller;

import com.example.demo.entity.Classroom;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/classrooms")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomRepository classroomRepository;

    // API Xóa lớp học theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClassroom(@PathVariable Long id) {
        // 1. Kiểm tra lớp học có tồn tại không
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy lớp học với ID: " + id));

        // 2. Thực hiện xóa
        // Nhờ CascadeType.ALL, các Student liên quan cũng sẽ bị xóa tự động trong DB
        classroomRepository.delete(classroom);

        // 3. Trả về 204 No Content (Thành công nhưng không có nội dung trả về)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}