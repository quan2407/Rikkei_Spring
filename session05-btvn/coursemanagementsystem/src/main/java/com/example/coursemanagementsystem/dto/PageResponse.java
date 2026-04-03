package com.example.coursemanagementsystem.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse<T> {
    List<T> items;
    int page;
    int size;
    long totalItems;
    int totalPages;
    boolean isLast;
}
