package com.example.demo.entity;

import lombok.*;
import java.time.LocalDate;

@Data // Bao gồm @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode
@NoArgsConstructor // Tạo constructor không đối số (bắt buộc cho Hibernate/JPA)
@AllArgsConstructor // Tạo constructor với đầy đủ các tham số
@Builder // Hỗ trợ tạo object theo pattern builder (rất hữu ích khi test)
public class Student {
    private String name;
    private String email;
    private LocalDate dob; // Date of Birth - dùng LocalDate cho chuẩn Java 8+
    private String address;
}