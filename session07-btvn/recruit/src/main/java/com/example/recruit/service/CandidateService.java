package com.example.recruit.service;

import com.example.recruit.dto.CandidateCreateDTO;
import com.example.recruit.dto.CandidateUpdateDTO;
import com.example.recruit.entity.Candidate;
import com.example.recruit.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;

    @Transactional
    public Candidate createCandidate(CandidateCreateDTO dto) {
        Candidate candidate = Candidate.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .age(dto.getAge())
                .yearsOfExperience(dto.getYearsOfExperience())
                .build();

        return candidateRepository.save(candidate);
    }
    @Transactional
    public Candidate updateCandidateInfo(Long id, CandidateUpdateDTO dto) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ứng viên với ID: " + id));

        candidate.setAddress(dto.getAddress());
        candidate.setBio(dto.getBio());

        return candidateRepository.save(candidate);
    }
}