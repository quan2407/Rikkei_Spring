package com.example.bookstore.repository;

import com.example.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

// BookRepository.java
public interface BookRepository extends JpaRepository<Book, Long> {
}