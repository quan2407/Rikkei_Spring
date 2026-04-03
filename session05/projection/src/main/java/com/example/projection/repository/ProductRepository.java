package com.example.projection.repository;

import com.example.projection.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Spring sẽ tự hiểu và kết hợp cả phân trang + sắp xếp từ đối tượng Pageable
    Page<Product> findByCategory(String category, Pageable pageable);

    // Nếu muốn lấy tất cả có phân trang
    Page<Product> findAll(Pageable pageable);
}