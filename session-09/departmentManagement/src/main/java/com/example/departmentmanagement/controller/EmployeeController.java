package com.example.departmentmanagement.controller;

import com.example.departmentmanagement.dto.ApiResponse;
import com.example.departmentmanagement.dto.EmployeeCreateDTO;
import com.example.departmentmanagement.entity.Employee;
import com.example.departmentmanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ApiResponse<Employee> createEmployee(@Valid @RequestBody EmployeeCreateDTO dto) {
        Employee savedEmp = employeeService.createEmployee(dto);
        return ApiResponse.<Employee>builder()
                .status("SUCCESS")
                .message("Thêm nhân viên thành công")
                .data(savedEmp)
                .build();
    }
    @PutMapping("/{id}/avatar")
    public ApiResponse<String> uploadAvatar(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String fileName = employeeService.uploadAvatar(id, file);
        return ApiResponse.<String>builder()
                .status("SUCCESS")
                .message("Upload ảnh đại diện thành công")
                .data(fileName)
                .build();
    }
}