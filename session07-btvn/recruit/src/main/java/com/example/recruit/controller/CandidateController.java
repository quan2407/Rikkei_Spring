package com.example.recruit.controller;

import com.example.recruit.dto.ApiResponse;
import com.example.recruit.dto.CandidateCreateDTO;
import com.example.recruit.dto.CandidateUpdateDTO;
import com.example.recruit.entity.Candidate;
import com.example.recruit.service.CandidateService; // Import Service mới
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<ApiResponse<Candidate>> createCandidate(
            @Valid @RequestBody CandidateCreateDTO dto) {

        Candidate savedCandidate = candidateService.createCandidate(dto);

        ApiResponse<Candidate> response = ApiResponse.<Candidate>builder()
                .status("success")
                .message("Đăng ký ứng viên thành công")
                .data(savedCandidate)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Candidate>> updateCandidate(
            @PathVariable Long id,
            @Valid @ModelAttribute CandidateUpdateDTO dto) {

        Candidate updatedCandidate = candidateService.updateCandidateInfo(id, dto);

        ApiResponse<Candidate> response = ApiResponse.<Candidate>builder()
                .status("success")
                .message("Cập nhật thông tin thành công")
                .data(updatedCandidate)
                .build();

        return ResponseEntity.ok(response);
    }
}