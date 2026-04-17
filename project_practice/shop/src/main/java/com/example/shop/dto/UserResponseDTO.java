package com.example.shop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class UserResponseDTO {
    private Long user_id;
    private String username;
    private String email;
    private String fullname;
    private Boolean status;
    private String phone;
    private String address;
    private Set<RoleDTO> roles;
}
