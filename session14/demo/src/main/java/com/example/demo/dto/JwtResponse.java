package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String accessToken;
    private String type = "Bearer";
    private String username;

    // Constructor tùy chỉnh nếu bạn muốn gọi nhanh trong AuthController
    public JwtResponse(String accessToken, String username) {
        this.accessToken = accessToken;
        this.username = username;
    }
}
