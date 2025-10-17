package com.julio.desafio.controller;

import com.julio.desafio.dtos.TaskResponse;
import com.julio.desafio.dtos.TaskRequest;
import com.julio.desafio.dtos.TaskUpdateRequest;
import com.julio.desafio.entity.Task;
import com.julio.desafio.entity.Task;
import com.julio.desafio.mapper.TaskMapper;
import com.julio.desafio.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tasks")
@RestController
public class TaskController {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskService taskService;
    
    @PostMapping
    @Operation(summary = "Create some task")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest){
        Task createdTask = taskService.createTask(taskRequest);
        TaskResponse response = taskMapper.toResponse(createdTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update some task")
    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateTask(@Valid @PathVariable Long id, @RequestBody TaskUpdateRequest taskUpdateRequest){
        Task updatedTask = taskService.updateTask(id,taskUpdateRequest);
        TaskResponse response = taskMapper.toResponse(updatedTask);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Delete some task")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@Valid @PathVariable Long id){
         taskService.deleteTask(id);
         return  ResponseEntity.noContent().build();
    }
}
