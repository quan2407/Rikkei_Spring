package com.example.carparkmanagement.controller;

import com.example.carparkmanagement.dto.*;
import com.example.carparkmanagement.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final ParkingService parkingService;

    @PostMapping("/check-in")
    public ResponseEntity<ApiResponse<TicketResponse>> checkIn(@RequestBody TicketRequest req) {
        TicketResponse data = parkingService.checkIn(req);
        return ResponseEntity.ok(new ApiResponse<>(true, "Xe vào bãi thành công", data));
    }

    @PutMapping("/check-out/{vehicleId}")
    public ResponseEntity<ApiResponse<TicketResponse>> checkOut(@PathVariable Long vehicleId) {
        TicketResponse data = parkingService.checkOut(vehicleId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Xe ra bãi thành công", data));
    }

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<List<TicketSummaryResponse>>> getSummary() {
        List<TicketSummaryResponse> data = parkingService.getTodaySummary();
        return ResponseEntity.ok(ApiResponse.<List<TicketSummaryResponse>>builder()
                .success(true)
                .message("Lấy danh sách vé trong ngày thành công")
                .data(data)
                .build());
    }
}