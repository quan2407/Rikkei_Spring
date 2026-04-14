package com.example.clinicmanagement.exception;

import com.example.clinicmanagement.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Xử lý tất cả các lỗi không được định nghĩa cụ thể (Lỗi 500)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(Exception e) {

        // YÊU CẦU: Ghi log.error kèm theo Object Exception 'e'
        // Logback sẽ tự động in toàn bộ Stack Trace giúp ta biết chính xác dòng lỗi.
        log.error("Lỗi hệ thống nghiêm trọng xảy ra: ", e);

        // Tạo cấu hình Response trả về cho Client (Ẩn đi thông tin chi tiết của lỗi)
        ApiResponse<Object> response = ApiResponse.builder()
                .status("error")
                .code(500)
                .data(null)
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}