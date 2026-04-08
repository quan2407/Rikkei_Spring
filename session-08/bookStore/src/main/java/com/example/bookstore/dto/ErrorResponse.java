package com.example.bookstore.dto;

// ErrorResponse.java
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private int status;           // mã HTTP (404, 400, ...)
    private String message;       // thông báo lỗi
    private LocalDateTime timestamp;   // thời gian xảy ra lỗi
}
