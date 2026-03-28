package com.example.taskmanager.repository;

import com.example.taskmanager.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>(Arrays.asList(
            new User(1L, "quan_nguyen", "quan@fpt.edu.vn", "ADMIN"),
            new User(2L, "bridge_eng", "korea@dev.com", "USER"),
            new User(3L, "fresher_dev", "test@gmail.com", "USER")
    ));

    public List<User> findAll() { return users; }

    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }
}
