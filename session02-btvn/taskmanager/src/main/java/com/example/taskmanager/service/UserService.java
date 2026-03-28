package com.example.taskmanager.service;

import com.example.taskmanager.models.User;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsers(String search) {
        List<User> allUsers = userRepository.findAll();

        if (search == null || search.isEmpty()) {
            return allUsers;
        }

        return allUsers.stream()
                .filter(user -> user.getUsername().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}
