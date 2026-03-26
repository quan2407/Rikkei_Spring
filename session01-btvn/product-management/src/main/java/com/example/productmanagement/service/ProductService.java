package com.example.productmanagement.service;

import com.example.productmanagement.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>(List.of(
            new Product("P01", "Laptop Lenovo Legion 5", 25000000f),
            new Product("P02", "Chuột Gaming", 1200000f),
            new Product("P03", "Bàn phím cơ", 3500000f)
    ));

    public List<Product> getAllProducts() {
        return products;
    }

    // THÊM MỚI
    public void addProduct(Product product) {
        products.add(product);
    }

    // CẬP NHẬT
    public boolean updateProduct(String id, Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                // Giữ nguyên ID cũ, cập nhật các trường khác
                updatedProduct.setId(id);
                products.set(i, updatedProduct);
                return true;
            }
        }
        return false;
    }

    // XÓA
    public boolean deleteProduct(String id) {
        return products.removeIf(p -> p.getId().equals(id));
    }
}