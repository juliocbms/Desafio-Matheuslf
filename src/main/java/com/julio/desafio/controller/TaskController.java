package com.julio.desafio.controller;

import com.julio.desafio.dtos.TaskResponse;
import com.julio.desafio.dtos.TaskRequest;
import com.julio.desafio.entity.Task;
import com.julio.desafio.entity.Task;
import com.julio.desafio.mapper.TaskMapper;
import com.julio.desafio.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/tasks")
@RestController
public class TaskController {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskService taskService;
    
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest){
        Task createdTask = taskService.createTask(taskRequest);
        TaskResponse response = taskMapper.toResponse(createdTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
