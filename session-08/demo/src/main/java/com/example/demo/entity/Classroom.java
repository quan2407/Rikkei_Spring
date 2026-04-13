package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "classrooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String className;

    private String roomNumber;

    // Quan hệ 1-N: Một lớp có nhiều sinh viên
    // mappedBy trỏ tới tên biến "classroom" ở trong class Student
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude // Loại bỏ để tránh lỗi vòng lặp vô hạn khi in log
    private List<Student> students;
}
