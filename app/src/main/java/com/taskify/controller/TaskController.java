package com.taskify.controller;

import com.taskify.model.Task;
import com.taskify.service.TaskService;
import com.taskify.dto.ApiResponse;
import com.taskify.dto.TaskRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

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
    public ApiResponse createTask(@RequestBody @Valid TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());

        Task createdTask = taskService.createTask(task);
        Map<String, Object> payload = new HashMap<>();
        payload.put("task", createdTask);
        return ApiResponse.success(payload);
    }
    
    @PutMapping("/{id}")
    public ApiResponse updateTask(@PathVariable Long id, @RequestBody @Valid TaskRequest request) {
        Task updatedTask = taskService.updateTask(id, request);
        Map<String, Object> payload = new HashMap<>();
        payload.put("task", updatedTask);
        return ApiResponse.success(payload);
    }
    
    @DeleteMapping("/{id}")
    public ApiResponse deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        Map<String, Object> payload = new HashMap<>();
        return ApiResponse.success(payload);
    }

    @GetMapping
    public ApiResponse getAllTasks(@RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "20") int perPage,
                                   @RequestParam(defaultValue = "title,asc") String sort) {

        String[] sortParams = sort.split(",");
        Sort.Direction direction = Sort.Direction.ASC;
        if (sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }
        Pageable pageable = PageRequest.of(page - 1, perPage, Sort.by(direction, sortParams[0]));
        Page<Task> tasks = taskService.getAllTasks(pageable);

        Map<String, Object> payload = new HashMap<>();
        payload.put("tasks", tasks.getContent());
        payload.put("total", tasks.getTotalElements());
        payload.put("page", tasks.getNumber() + 1);
        payload.put("perPage", tasks.getSize());

        return ApiResponse.success(payload);
    }
}
