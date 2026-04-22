package com.example.hrm.controller;

import com.example.hrm.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @GetMapping("/api/v1/employees")
    public List<Employee> getAllEmployees() {
        return List.of(
                new Employee(1L, "Nguyen Van A", 1000.0),
                new Employee(2L, "Tran Thi B", 1200.0),
                new Employee(3L, "Le Van C", 1500.0)
        );
    }
}