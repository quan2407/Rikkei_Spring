package com.example.recruit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CandidateUpdateDTO {

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @Size(max = 200, message = "Giới thiệu bản thân không quá 200 ký tự")
    private String bio;
}