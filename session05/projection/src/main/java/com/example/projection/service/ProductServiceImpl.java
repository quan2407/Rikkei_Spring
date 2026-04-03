package com.example.projection.service.impl;

import com.example.projection.entity.Product;
import com.example.projection.repository.ProductRepository;
import com.example.projection.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // Tự động inject ProductRepository qua constructor nhờ Lombok
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Page<Product> getAllProducts(String category, Pageable pageable) {
        if (category != null && !category.isEmpty()) {
            return productRepository.findByCategory(category, pageable);
        }
        return productRepository.findAll(pageable);
    }
}