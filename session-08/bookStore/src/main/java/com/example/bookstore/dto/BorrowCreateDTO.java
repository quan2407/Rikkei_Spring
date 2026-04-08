package com.example.bookstore.dto;

// BorrowCreateDTO.java
import com.example.bookstore.annotation.ExistingBookId;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowCreateDTO {

    @NotBlank(message = "Tên người dùng không được để trống")
    private String username;

    @ExistingBookId
    private Long bookId;
}
