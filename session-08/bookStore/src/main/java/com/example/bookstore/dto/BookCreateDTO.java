package com.example.bookstore.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

// BookCreateDTO.java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateDTO {

    private String title;
    private String author;
    private Integer stock;

    private MultipartFile coverImage;
}
