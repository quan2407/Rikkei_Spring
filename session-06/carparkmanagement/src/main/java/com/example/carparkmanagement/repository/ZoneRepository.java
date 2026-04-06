package com.example.carparkmanagement.repository;

import com.example.carparkmanagement.dto.ZoneStatisticsResponse;
import com.example.carparkmanagement.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    @Query("SELECT new com.example.carparkmanagement.dto.ZoneStatisticsResponse(" +
            "z.id, z.name, z.capacity, z.occupiedSpots, (z.capacity - z.occupiedSpots)) " +
            "FROM Zone z")
    List<ZoneStatisticsResponse> getZoneStatistics();
}