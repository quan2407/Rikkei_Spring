package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @Column(unique = true, nullable = false)
    private String sku = UUID.randomUUID().toString();

    @Column(unique = true, length = 100, nullable = false)
    private String product_name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal unit_price;

    private Integer stock_quantity;

    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDate created_at = LocalDate.now();
    private LocalDate updated_at;
}