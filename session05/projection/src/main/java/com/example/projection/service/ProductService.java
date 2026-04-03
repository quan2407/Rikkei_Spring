package com.example.projection.service;

import com.example.projection.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> getAllProducts(String category, Pageable pageable);
}