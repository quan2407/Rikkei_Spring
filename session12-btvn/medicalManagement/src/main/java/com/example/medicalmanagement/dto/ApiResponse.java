package com.example.medicalmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
    private PageResponse metadata;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PageResponse {
        private int currentPage;
        private int totalPages;
        private long totalElements;
    }
}