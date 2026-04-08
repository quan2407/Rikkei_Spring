package com.example.bookstore.service;

import com.example.bookstore.dto.BookCreateDTO;
import com.example.bookstore.dto.BookUpdateStockDTO;
import com.example.bookstore.entity.Book;
import com.example.bookstore.exception.ResourceNotFoundException;
import com.example.bookstore.repository.BookRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final String UPLOAD_DIR = "uploads/";
    public Book createBook(BookCreateDTO dto){
        String coverUrl = null;
        if (dto.getCoverImage()!=null && !dto.getCoverImage().isEmpty()){
            coverUrl = saveFile(dto.getCoverImage());
        }
        Book book = Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .stock(dto.getStock())
                .coverUrl(coverUrl)
                .build();
        return bookRepository.save(book);
    }

    private String saveFile(MultipartFile file) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFilename = UUID.randomUUID().toString() + fileExtension;

            // Lưu file vào thư mục
            Path filePath = uploadPath.resolve(newFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "/uploads/" + newFilename;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Book updateBook(Long id, @Valid BookUpdateStockDTO dto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));
        book.setStock(dto.getStock());
        return bookRepository.save(book);
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("not found")));
    }

}
