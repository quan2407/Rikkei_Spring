package com.example.shop.controller;

import com.example.shop.dto.ApiResponse;
import com.example.shop.dto.PageResponse;
import com.example.shop.dto.RoleDTO;
import com.example.shop.dto.UserResponseDTO;
import com.example.shop.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminUserService adminService;

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<PageResponse<UserResponseDTO>>> getAllUsers(
            @PageableDefault(sort = "user_id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.<PageResponse<UserResponseDTO>>builder()
                .message("Lấy danh sách người dùng thành công")
                .data(adminService.getAllUsers(pageable))
                .build());
    }

    @PostMapping("/users/{userId}/role/{roleId}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> addRole(@PathVariable Long userId, @PathVariable Long roleId) {
        return ResponseEntity.ok(ApiResponse.<UserResponseDTO>builder()
                .message("Đã thêm quyền thành công")
                .data(adminService.addRoleToUser(userId, roleId))
                .build());
    }

    @DeleteMapping("/users/{userId}/role/{roleId}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> removeRole(@PathVariable Long userId, @PathVariable Long roleId) {
        return ResponseEntity.ok(ApiResponse.<UserResponseDTO>builder()
                .message("Đã xóa quyền thành công")
                .data(adminService.removeRoleFromUser(userId, roleId))
                .build());
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> toggleStatus(@PathVariable Long userId) {
        return ResponseEntity.ok(ApiResponse.<UserResponseDTO>builder()
                .message("Cập nhật trạng thái thành công")
                .data(adminService.toggleUserStatus(userId))
                .build());
    }

    @GetMapping("/roles")
    public ResponseEntity<ApiResponse<List<RoleDTO>>> getAllRoles() {
        return ResponseEntity.ok(ApiResponse.<List<RoleDTO>>builder()
                .message("Lấy danh sách quyền thành công")
                .data(adminService.getAllRoles())
                .build());
    }

    @GetMapping("/users/search")
    public ResponseEntity<ApiResponse<PageResponse<UserResponseDTO>>> searchUser(
            @RequestParam String name, Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.<PageResponse<UserResponseDTO>>builder()
                .message("Tìm kiếm hoàn tất")
                .data(adminService.searchUsers(name, pageable))
                .build());
    }
}