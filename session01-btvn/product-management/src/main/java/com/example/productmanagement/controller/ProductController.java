package com.example.productmanagement.controller;

import com.example.productmanagement.model.Product;
import com.example.productmanagement.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> listAll() {
        return productService.getAllProducts();
    }

    // 1. THÊM MỚI: POST /api/products
    @PostMapping
    public String add(@RequestBody Product product) {
        productService.addProduct(product);
        return "Thêm sản phẩm thành công!";
    }

    // 2. CẬP NHẬT: PUT /api/products/{id}
    @PutMapping("/{id}")
    public String update(@PathVariable String id, @RequestBody Product product) {
        boolean isUpdated = productService.updateProduct(id, product);
        return isUpdated ? "Cập nhật thành công!" : "Không tìm thấy sản phẩm để cập nhật.";
    }

    // 3. XÓA: DELETE /api/products/{id}
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        boolean isDeleted = productService.deleteProduct(id);
        return isDeleted ? "Xóa thành công!" : "Không tìm thấy sản phẩm để xóa.";
    }
}