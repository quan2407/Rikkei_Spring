package com.example.milktea.service;

import com.example.milktea.entity.User;
import com.example.milktea.repository.UserRepository;
import com.example.milktea.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public String register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) throw new RuntimeException("Email đã tồn tại");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "Đăng ký thành công!";
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));
        if (passwordEncoder.matches(password, user.getPassword())) {
            return jwtProvider.generateToken(email);
        }
        throw new RuntimeException("Mật khẩu sai");
    }
}