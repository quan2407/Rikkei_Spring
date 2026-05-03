package com.example.milktea.controller;

import com.example.milktea.entity.User;
import com.example.milktea.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginReq) {
        String token = authService.login(loginReq.getEmail(), loginReq.getPassword());
        return ResponseEntity.ok("Bearer " + token);
    }
}