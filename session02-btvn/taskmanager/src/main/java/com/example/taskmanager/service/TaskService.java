package com.example.taskmanager.service;

import com.example.taskmanager.models.Task;
import com.example.taskmanager.models.User;
import com.example.taskmanager.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;


    public List<Task> findTasks(String search) {
        List<Task> allTasks = taskRepository.findAll();

        if (search == null || search.isEmpty()) {
            return allTasks;
        }

        return allTasks.stream()
                .filter(task -> task.getTitle().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }
    public Optional<Task> createTask(Task newTask) {
        // Kiểm tra xem User được gán (assignedTo) có tồn tại không
        Optional<User> userOpt = userService.findUserById(newTask.getAssignedTo());

        if (userOpt.isPresent()) {
            // Nếu tồn tại, lưu task và trả về task đã tạo
            return Optional.of(taskRepository.save(newTask));
        }

        // Nếu không tồn tại, trả về rỗng để Controller xử lý lỗi
        return Optional.empty();
    }
}
