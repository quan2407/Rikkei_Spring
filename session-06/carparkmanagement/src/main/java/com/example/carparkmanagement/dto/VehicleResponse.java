package com.example.carparkmanagement.dto;

import com.example.carparkmanagement.constant.VehicleType;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class VehicleResponse {
    private Long id;
    private String licensePlate;
    private String color;
    private VehicleType vehicleType;
}