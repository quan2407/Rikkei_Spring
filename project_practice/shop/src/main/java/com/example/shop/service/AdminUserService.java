package com.example.shop.service;

import com.example.shop.dto.PageResponse;
import com.example.shop.dto.RoleDTO;
import com.example.shop.dto.UserResponseDTO;
import com.example.shop.entity.Role;
import com.example.shop.entity.User;
import com.example.shop.repository.RoleRepository;
import com.example.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public PageResponse<UserResponseDTO> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return mapToPageResponse(users);
    }

    public PageResponse<UserResponseDTO> searchUsers(String name, Pageable pageable) {
        Page<User> users = userRepository.findByFullnameContainingIgnoreCase(name, pageable);
        return mapToPageResponse(users);
    }

    public UserResponseDTO addRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(role);
        return mapToDTO(userRepository.save(user));
    }

    public UserResponseDTO removeRoleFromUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().remove(role);
        return mapToDTO(userRepository.save(user));
    }

    public UserResponseDTO toggleUserStatus(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(!user.getStatus());
        return mapToDTO(userRepository.save(user));
    }

    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(role -> RoleDTO.builder()
                        .role_id(role.getRole_id())
                        .role_name(role.getRole_name().name())
                        .build())
                .collect(Collectors.toList());
    }

    // Helper: Map Entity to DTO
    private UserResponseDTO mapToDTO(User user) {
        return UserResponseDTO.builder()
                .user_id(user.getUser_id())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullname(user.getFullname())
                .status(user.getStatus())
                .phone(user.getPhone())
                .address(user.getAddress())
                .roles(user.getRoles().stream().map(r -> RoleDTO.builder()
                        .role_id(r.getRole_id())
                        .role_name(r.getRole_name().name()).build()).collect(Collectors.toSet()))
                .build();
    }

    // Helper: Map Page Entity to PageResponse DTO
    private PageResponse<UserResponseDTO> mapToPageResponse(Page<User> page) {
        List<UserResponseDTO> dtos = page.getContent().stream().map(this::mapToDTO).toList();
        return PageResponse.<UserResponseDTO>builder()
                .content(dtos)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }
}