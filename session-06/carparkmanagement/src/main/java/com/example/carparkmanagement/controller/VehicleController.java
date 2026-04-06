package com.example.carparkmanagement.controller;

import com.example.carparkmanagement.dto.*;
import com.example.carparkmanagement.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<ApiResponse<VehicleResponse>> create(@RequestBody VehicleCreateRequest request) {
        return ResponseEntity.ok(ApiResponse.<VehicleResponse>builder()
                .success(true)
                .message("Created successfully")
                .data(vehicleService.createVehicle(request))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<VehicleResponse>>> getVehicles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String keyword
    ) {
        PageResponse<VehicleResponse> data = vehicleService.getPagedVehicles(page, size, sortBy, direction, keyword);

        return ResponseEntity.ok(ApiResponse.<PageResponse<VehicleResponse>>builder()
                .success(true)
                .message("Fetch data successfully")
                .data(data)
                .build());
    }
}