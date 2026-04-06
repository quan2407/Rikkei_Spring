package com.example.recruit.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CandidateCreateDTO {

    @NotBlank(message = "Tên ứng viên không được để trống")
    @Size(min = 5, max = 50, message = "Tên phải từ 5 đến 50 ký tự")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @Min(value = 18, message = "Ứng viên phải từ 18 tuổi trở lên")
    private Integer age;

    @Min(value = 0, message = "Số năm kinh nghiệm không được là số âm")
    private Integer yearsOfExperience;
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(
            regexp = "^(03|05|07|08|09)\\d{8}$",
            message = "Số điện thoại không hợp lệ (phải gồm 10 số và bắt đầu bằng 03, 05, 07, 08 hoặc 09)"
    )
    private String phone;
}