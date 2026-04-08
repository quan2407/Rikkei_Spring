package com.example.bookstore.controller;

// BorrowController.java
import com.example.bookstore.dto.BorrowCreateDTO;
import com.example.bookstore.entity.Borrow;
import com.example.bookstore.service.BorrowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrows")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    @PostMapping
    public ResponseEntity<Borrow> createBorrow(@Valid @RequestBody BorrowCreateDTO dto) {
        Borrow savedBorrow = borrowService.createBorrow(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBorrow);
    }
}
