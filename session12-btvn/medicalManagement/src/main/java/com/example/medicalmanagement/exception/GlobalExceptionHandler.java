package com.example.medicalmanagement.exception;

import com.example.medicalmanagement.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleAll(Exception e) {
        log.error("Hệ thống xảy ra lỗi: ", e); // Ghi StackTrace vào file
        return ResponseEntity.status(500).body(ApiResponse.<Void>builder()
                .status(500).message("Internal Server Error").build());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntime(RuntimeException e) {
        return ResponseEntity.badRequest().body(ApiResponse.<Void>builder()
                .status(400).message(e.getMessage()).build());
    }
}