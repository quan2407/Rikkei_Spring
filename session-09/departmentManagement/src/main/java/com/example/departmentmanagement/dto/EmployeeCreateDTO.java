package com.example.departmentmanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmployeeCreateDTO {

    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;

    @Email(message = "Email không đúng định dạng")
    @NotBlank(message = "Email không được để trống")
    private String email;

    @Pattern(regexp = "^(03|05|07|08|09)\\d{8}$", message = "Số điện thoại không hợp lệ (phải có 10 số và bắt đầu bằng 03,05,07,08,09)")
    private String phone;

    @Min(value = 5000000, message = "Lương tối thiểu phải là 5.000.000 VNĐ")
    private Double salary;

    @NotNull(message = "ID phòng ban không được để trống")
    private Long departmentId;
}