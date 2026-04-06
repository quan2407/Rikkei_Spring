package com.example.carparkmanagement.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class TicketResponse {
    private Long id;
    private String licensePlate;
    private String zoneName;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
}