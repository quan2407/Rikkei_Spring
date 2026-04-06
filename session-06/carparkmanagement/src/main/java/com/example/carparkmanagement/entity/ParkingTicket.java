package com.example.carparkmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_tickets")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ParkingTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;

    @Column(name = "check_out_time")
    private LocalDateTime checkOutTime;

    // Quan hệ Many-to-One với Vehicle (nắm giữ khóa ngoại)
    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    // Quan hệ Many-to-One với Zone (nắm giữ khóa ngoại)
    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)
    private Zone zone;
}