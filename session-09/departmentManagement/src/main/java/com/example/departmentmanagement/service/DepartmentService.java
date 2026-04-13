package com.example.departmentmanagement.service;

import com.example.departmentmanagement.dto.DepartmentDTO;
import com.example.departmentmanagement.entity.Department;
import com.example.departmentmanagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(DepartmentDTO dto) {
        Department entity = new Department();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        return departmentRepository.save(entity);
    }
}