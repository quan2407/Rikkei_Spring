package com.example.recruit.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;       
}