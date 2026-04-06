package com.example.carparkmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TicketRequest {
    private Long vehicleId;
    private Long zoneId;
}