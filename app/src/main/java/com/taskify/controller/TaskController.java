package com.taskify.controller;

import com.taskify.model.Task;
import com.taskify.service.TaskService;
import com.taskify.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ApiResponse createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        Map<String, Object> payload = new HashMap<>();
        payload.put("task", createdTask);
        return ApiResponse.success(payload);
    }

    @GetMapping
    public ApiResponse getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        Map<String, Object> payload = new HashMap<>();
        payload.put("tasks", tasks);
        return ApiResponse.success(payload);
    }
}
