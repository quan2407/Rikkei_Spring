package com.example.departmentmanagement.controller;

import com.example.departmentmanagement.dto.ApiResponse;
import com.example.departmentmanagement.dto.DepartmentDTO;
import com.example.departmentmanagement.entity.Department;
import com.example.departmentmanagement.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ApiResponse<Department> createDepartment(@Valid @RequestBody DepartmentDTO dto) {
        Department savedDept = departmentService.saveDepartment(dto);

        return ApiResponse.<Department>builder()
                .status("SUCCESS")
                .message("Tạo phòng ban thành công")
                .data(savedDept)
                .build();
    }
}