package com.example.carparkmanagement.repository;

import com.example.carparkmanagement.dto.TicketSummaryResponse;
import com.example.carparkmanagement.entity.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {
    // Tìm vé mới nhất của xe mà chưa có thời gian ra (checkOutTime IS NULL)
    Optional<ParkingTicket> findFirstByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(Long vehicleId);
    @Query("SELECT new com.example.carparkmanagement.dto.TicketSummaryResponse(" +
            "t.id, v.licensePlate, z.name, t.checkInTime, t.checkOutTime) " +
            "FROM ParkingTicket t " +
            "JOIN t.vehicle v " +
            "JOIN t.zone z " +
            "WHERE CAST(t.checkInTime AS date) = CURRENT_DATE")
    List<TicketSummaryResponse> findAllSummaryToday();
}