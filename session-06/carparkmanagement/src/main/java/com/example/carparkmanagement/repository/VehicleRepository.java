package com.example.carparkmanagement.repository;

import com.example.carparkmanagement.dto.VehicleResponse;
import com.example.carparkmanagement.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT new com.example.carparkmanagement.dto.VehicleResponse(v.id, v.licensePlate, v.color, v.type) " +
            "FROM Vehicle v " +
            "WHERE (:keyword IS NULL OR v.licensePlate LIKE %:keyword% OR v.color LIKE %:keyword%)")
    Page<VehicleResponse> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);
}