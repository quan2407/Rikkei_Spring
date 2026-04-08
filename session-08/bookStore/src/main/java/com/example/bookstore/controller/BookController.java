package com.example.bookstore.controller;

import com.example.bookstore.dto.BookCreateDTO;
import com.example.bookstore.dto.BookUpdateStockDTO;
import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @PostMapping
    public ResponseEntity<Book> createBook(@ModelAttribute BookCreateDTO bookCreateDTO){
        Book savedBook = bookService.createBook(bookCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Book> updateBookStock(
            @PathVariable Long id,
            @Valid @RequestBody BookUpdateStockDTO dto
            ) {
        Book updatedBook = bookService.updateBook(id,dto);
        return ResponseEntity.ok(updatedBook);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }
}
