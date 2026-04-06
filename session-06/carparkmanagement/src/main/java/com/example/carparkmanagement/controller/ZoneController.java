package com.example.carparkmanagement.controller;

import com.example.carparkmanagement.dto.ApiResponse;
import com.example.carparkmanagement.dto.ZoneStatisticsResponse;
import com.example.carparkmanagement.service.ZoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ZoneController {

    private final ZoneService zoneService;

    @GetMapping("/v1/zones/stats")
    public ResponseEntity<ApiResponse<List<ZoneStatisticsResponse>>> getStatsV1() {
        long start = System.currentTimeMillis();
        List<ZoneStatisticsResponse> data = zoneService.getStatsV1();
        log.info("V1 (Java calculation) took: {} ms", System.currentTimeMillis() - start);

        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy thống kê V1 thành công", data));
    }

    @GetMapping("/v2/zones/stats")
    public ResponseEntity<ApiResponse<List<ZoneStatisticsResponse>>> getStatsV2() {
        long start = System.currentTimeMillis();
        List<ZoneStatisticsResponse> data = zoneService.getStatsV2();
        log.info("V2 (JPQL calculation) took: {} ms", System.currentTimeMillis() - start);

        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy thống kê V2 thành công", data));
    }
}