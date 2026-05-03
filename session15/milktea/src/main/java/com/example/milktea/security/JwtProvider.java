package com.example.milktea.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {
    private final String SECRET_STR = "milktea-secret-key-2026-backend-developer-training";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_STR.getBytes());

    // Thêm tham số role vào đây
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .claim("role", role) // Lưu role vào token
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token).getPayload();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}