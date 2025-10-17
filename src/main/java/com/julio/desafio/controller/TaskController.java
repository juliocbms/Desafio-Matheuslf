package com.julio.desafio.controller;

import com.julio.desafio.dtos.TaskResponse;
import com.julio.desafio.dtos.TaskRequest;
import com.julio.desafio.dtos.TaskUpdateRequest;
import com.julio.desafio.entity.Task;
import com.julio.desafio.mapper.TaskMapper;
import com.julio.desafio.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tasks")
@RestController
@Tag(name = "Tarefas", description = "Endpoints para criar e gerenciar tarefas")
public class TaskController {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskService taskService;

    @Operation(
            summary = "Cria uma nova tarefa",
            description = "Registra uma nova tarefa associada a um projeto existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Projeto associado não encontrado", content = @Content)
    })
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest){
        Task createdTask = taskService.createTask(taskRequest);
        TaskResponse response = taskMapper.toResponse(createdTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Atualiza o status de uma tarefa",
            description = "Altera o status de uma tarefa existente (ex: de TODO para IN_PROGRESS)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status da tarefa atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada com o ID informado")
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(
            @Parameter(description = "ID da tarefa a ser atualizada", required = true, example = "1")
            @PathVariable Long id,
            @Valid @RequestBody TaskUpdateRequest taskStatusUpdateRequest){
        Task updatedTask = taskService.updateTask(id, taskStatusUpdateRequest);
        TaskResponse response = taskMapper.toResponse(updatedTask);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Deleta uma tarefa",
            description = "Remove uma tarefa do banco de dados com base no seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada com o ID informado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @Parameter(description = "ID da tarefa a ser deletada", required = true, example = "1")
            @PathVariable Long id){
        taskService.deleteTask(id);
        return  ResponseEntity.noContent().build();
    }
}