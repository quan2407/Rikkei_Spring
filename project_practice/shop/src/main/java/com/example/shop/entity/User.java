package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(unique = true, length = 100, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String fullname;

    private Boolean status = true;

    @Column(nullable = false)
    private String password;

    private String avatar;

    @Column(unique = true, length = 15)
    private String phone;

    @Column(nullable = false)
    private String address;

    private LocalDate created_at = LocalDate.now();
    private LocalDate updated_at;

    @Column(columnDefinition = "boolean default false")
    private Boolean is_deleted = false;

    // Trong User.java
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}