package com.example.carparkmanagement.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor // Bắt buộc phải có AllArgsConstructor cho JPQL
public class TicketSummaryResponse {
    private Long id;
    private String licensePlate;
    private String zoneName;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
}