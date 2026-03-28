package com.example.taskmanager.repository;

import com.example.taskmanager.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();

    public TaskRepository() {
        for (long i = 1; i <= 10; i++) {
            tasks.add(new Task(i, "Task " + i, "Description " + i, i % 3 == 0 ? "high" : "low", (i % 3) + 1));
        }
    }

    public List<Task> findAll() { return tasks; }

    public Task save(Task newTask) {
        tasks.add(newTask);
        return newTask;
    }
}
