package com.example.carparkmanagement.entity;

import com.example.carparkmanagement.constant.VehicleType;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate", unique = true, nullable = false)
    private String licensePlate;

    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type")
    private VehicleType type;

    @OneToMany(mappedBy = "vehicle")
    private List<ParkingTicket> parkingTickets;
}