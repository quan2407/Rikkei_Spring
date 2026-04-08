package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

// Book.java
@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private Integer stock;
    private String coverUrl;   // ví dụ: "/uploads/abc123.jpg"

}
