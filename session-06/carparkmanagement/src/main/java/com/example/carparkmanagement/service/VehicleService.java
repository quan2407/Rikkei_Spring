package com.example.carparkmanagement.service;

import com.example.carparkmanagement.dto.*;
import com.example.carparkmanagement.entity.Vehicle;
import com.example.carparkmanagement.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleResponse createVehicle(VehicleCreateRequest request) {
        Vehicle vehicle = Vehicle.builder()
                .licensePlate(request.getLicensePlate())
                .color(request.getColor())
                .type(request.getVehicleType())
                .build();
        Vehicle saved = vehicleRepository.save(vehicle);
        return new VehicleResponse(saved.getId(), saved.getLicensePlate(), saved.getColor(), saved.getType());
    }

    public PageResponse<VehicleResponse> getPagedVehicles(int page, int size, String sortBy, String direction, String keyword) {
        int validatedPage = Math.max(page, 0);

        Sort sort = Sort.unsorted();
        if (sortBy != null && !sortBy.isEmpty()) {
            Sort.Direction dir = (direction != null && direction.equalsIgnoreCase("DESC"))
                    ? Sort.Direction.DESC : Sort.Direction.ASC;
            sort = Sort.by(dir, sortBy);
        }

        Pageable pageable = PageRequest.of(validatedPage, size, sort);
        Page<VehicleResponse> pageResult = vehicleRepository.findAllByKeyword(keyword, pageable);

        return PageResponse.<VehicleResponse>builder()
                .items(pageResult.getContent())
                .page(pageResult.getNumber())
                .size(pageResult.getSize())
                .totalItems(pageResult.getTotalElements())
                .totalPages(pageResult.getTotalPages())
                .isLast(pageResult.isLast())
                .build();
    }
}