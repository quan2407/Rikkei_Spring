package com.example.bookstore.service;

// BorrowService.java
import com.example.bookstore.dto.BorrowCreateDTO;
import com.example.bookstore.entity.Borrow;
import com.example.bookstore.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;

    public Borrow createBorrow(BorrowCreateDTO dto) {
        Borrow borrow = Borrow.builder()
                .username(dto.getUsername())
                .bookId(dto.getBookId())
                .build();

        return borrowRepository.save(borrow);
    }
}