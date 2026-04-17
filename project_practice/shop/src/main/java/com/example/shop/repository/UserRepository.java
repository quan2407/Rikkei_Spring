package com.example.shop.repository;

import com.example.shop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Tìm kiếm theo tên (chứa từ khóa, không phân biệt hoa thường) có phân trang
    Page<User> findByFullnameContainingIgnoreCase(String fullname, Pageable pageable);
}
