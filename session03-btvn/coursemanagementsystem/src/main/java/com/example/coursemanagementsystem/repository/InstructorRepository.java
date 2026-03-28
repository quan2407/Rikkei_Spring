package com.example.coursemanagementsystem.repository;

import com.example.coursemanagementsystem.models.Instructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class InstructorRepository {
    private List<Instructor> instructors = new ArrayList<>(Arrays.asList(
            new Instructor(1L, "Dr. Thanh", "thanh@fpt.edu.vn"),
            new Instructor(2L, "Ms. Hoa", "hoa.korea@gmail.com")
    ));

    public List<Instructor> findAll() { return instructors; }
}
