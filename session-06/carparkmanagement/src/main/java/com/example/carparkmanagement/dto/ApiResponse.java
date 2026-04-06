package com.example.carparkmanagement.dto;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private T data;
}