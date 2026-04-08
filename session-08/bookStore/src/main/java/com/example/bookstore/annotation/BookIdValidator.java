package com.example.bookstore.annotation;

import com.example.bookstore.repository.BookRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookIdValidator implements ConstraintValidator<ExistingBookId, Long> {
    private final BookRepository bookRepository;

    @Override
    public boolean isValid(Long bookId, ConstraintValidatorContext constraintValidatorContext) {
        if (bookId == null) {
            return false;
        }
        return bookRepository.existsById(bookId);
    }
}
