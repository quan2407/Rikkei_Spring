package com.example.demo.controller;

import com.example.demo.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    // Giả lập database bằng một List tạm thời
    private static List<Student> studentList = new ArrayList<>();

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id, @RequestBody Student studentUpdate) {
        // 1. Tìm sinh viên trong danh sách dựa trên id (giả sử email hoặc một trường nào đó là id)
        // Ở đây mình dùng Stream API để tìm cho nhanh
        Student existingStudent = studentList.stream()
                .filter(s -> s.getEmail().equals(id)) // Giả định dùng Email làm ID để tìm kiếm
                .findFirst()
                .orElse(null);

        // 2. Kiểm tra nếu không tìm thấy thì trả về 404
        if (existingStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy sinh viên có ID: " + id);
        }

        // 3. Cập nhật thông tin mới vào đối tượng đã tìm thấy
        existingStudent.setName(studentUpdate.getName());
        existingStudent.setEmail(studentUpdate.getEmail());
        existingStudent.setDob(studentUpdate.getDob());
        existingStudent.setAddress(studentUpdate.getAddress());

        // 4. Trả về đối tượng đã cập nhật kèm mã 200 OK
        return ResponseEntity.ok(existingStudent);
    }
}