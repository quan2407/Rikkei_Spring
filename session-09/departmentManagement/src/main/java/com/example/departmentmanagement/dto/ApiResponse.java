package com.example.departmentmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Thuộc tính nào null thì không hiện trong JSON
public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;
}