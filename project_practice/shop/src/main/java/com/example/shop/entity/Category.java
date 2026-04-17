package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Column(length = 100, nullable = false)
    private String category_name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Boolean status = true;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}