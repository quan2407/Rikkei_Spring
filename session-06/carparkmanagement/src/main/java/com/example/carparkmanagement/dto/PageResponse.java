package com.example.carparkmanagement.dto;

import lombok.*;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class PageResponse<T> {
    private List<T> items;
    private Integer page;
    private Integer size;
    private Long totalItems;
    private Integer totalPages;
    private Boolean isLast;
}