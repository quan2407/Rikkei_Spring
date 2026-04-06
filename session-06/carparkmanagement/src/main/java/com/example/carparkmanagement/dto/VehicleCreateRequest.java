package com.example.carparkmanagement.dto;

import com.example.carparkmanagement.constant.VehicleType;
import lombok.*;
@Getter @Setter
public class VehicleCreateRequest {
    private String licensePlate;
    private String color;
    private VehicleType vehicleType;
}