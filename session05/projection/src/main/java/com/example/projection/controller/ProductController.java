package com.example.projection.controller;

import com.example.projection.entity.Product;
import com.example.projection.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<Product> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String category
    ) {
        // Tạo Sort logic
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        // Tạo Pageable
        Pageable pageable = PageRequest.of(page, size, sort);

        // Gọi Service thay vì Repository
        return productService.getAllProducts(category, pageable);
    }

    @GetMapping("/advanced")
    public Page<Product> getProductsAdvanced(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            // Nhận mảng sort theo định dạng: ["price,desc", "name,asc"]
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        List<Sort.Order> orders = new ArrayList<>();

        // Duyệt qua các tham số sort để build đối tượng Sort
        for (String sortOrder : sort) {
            String[] _sort = sortOrder.split(",");
            String property = _sort[0];
            Sort.Direction direction = _sort.length > 1 && _sort[1].equalsIgnoreCase("desc")
                    ? Sort.Direction.DESC
                    : Sort.Direction.ASC;
            orders.add(new Sort.Order(direction, property));
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        return productService.getAllProducts(null, pageable);
    }
}