package com.example.carparkmanagement.service;

import com.example.carparkmanagement.dto.*;
import com.example.carparkmanagement.entity.*;
import com.example.carparkmanagement.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingTicketRepository ticketRepository;
    private final VehicleRepository vehicleRepository;
    private final ZoneRepository zoneRepository;

    @Transactional
    public TicketResponse checkIn(TicketRequest req) {
        // 1. Kiểm tra tồn tại
        Vehicle vehicle = vehicleRepository.findById(req.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phương tiện!"));
        Zone zone = zoneRepository.findById(req.getZoneId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khu vực!"));

        // 2. Kiểm tra còn chỗ không
        if (zone.getOccupiedSpots() >= zone.getCapacity()) {
            throw new RuntimeException("Khu vực " + zone.getName() + " đã hết chỗ đỗ!");
        }

        // 3. Tạo vé mới
        ParkingTicket ticket = ParkingTicket.builder()
                .vehicle(vehicle)
                .zone(zone)
                .checkInTime(LocalDateTime.now())
                .build();

        // 4. Cập nhật số chỗ trong Zone
        zone.setOccupiedSpots(zone.getOccupiedSpots() + 1);
        zoneRepository.save(zone);

        ParkingTicket savedTicket = ticketRepository.save(ticket);

        return mapToResponse(savedTicket);
    }

    @Transactional
    public TicketResponse checkOut(Long vehicleId) {
        // 1. Tìm vé chưa check-out
        ParkingTicket ticket = ticketRepository.findFirstByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(vehicleId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lượt vào bãi chưa hoàn tất của xe này!"));

        // 2. Cập nhật thời gian ra
        ticket.setCheckOutTime(LocalDateTime.now());

        // 3. Giải phóng chỗ đỗ trong Zone
        Zone zone = ticket.getZone();
        if (zone.getOccupiedSpots() > 0) {
            zone.setOccupiedSpots(zone.getOccupiedSpots() - 1);
            zoneRepository.save(zone);
        }

        ParkingTicket updatedTicket = ticketRepository.save(ticket);

        return mapToResponse(updatedTicket);
    }
    public List<TicketSummaryResponse> getTodaySummary() {
        return ticketRepository.findAllSummaryToday();
    }
    private TicketResponse mapToResponse(ParkingTicket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .licensePlate(ticket.getVehicle().getLicensePlate())
                .zoneName(ticket.getZone().getName())
                .checkInTime(ticket.getCheckInTime())
                .checkOutTime(ticket.getCheckOutTime())
                .build();
    }
}