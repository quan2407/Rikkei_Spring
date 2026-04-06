package com.example.carparkmanagement.service;

import com.example.carparkmanagement.dto.ZoneStatisticsResponse;
import com.example.carparkmanagement.entity.Zone;
import com.example.carparkmanagement.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneRepository zoneRepository;

    public List<ZoneStatisticsResponse> getStatsV1() {
        List<Zone> zones = zoneRepository.findAll();
        return zones.stream().map(z -> ZoneStatisticsResponse.builder()
                .id(z.getId())
                .name(z.getName())
                .capacity(z.getCapacity())
                .occupiedSpots(z.getOccupiedSpots())
                .availableSpots(z.getCapacity() - z.getOccupiedSpots())
                .build()
        ).collect(Collectors.toList());
    }

    public List<ZoneStatisticsResponse> getStatsV2() {
        return zoneRepository.getZoneStatistics();
    }
}