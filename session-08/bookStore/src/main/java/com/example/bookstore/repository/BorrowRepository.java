package com.example.bookstore.repository;

// BorrowRepository.java
import com.example.bookstore.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
