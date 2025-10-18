package com.julio.desafio.service;

import com.julio.desafio.dtos.TaskRequest;
import com.julio.desafio.entity.Project;
import com.julio.desafio.entity.Task;
import com.julio.desafio.enums.Priority;
import com.julio.desafio.enums.Status;
import com.julio.desafio.mapper.TaskMapper;
import com.julio.desafio.repository.ProjectRepository;
import com.julio.desafio.repository.TaskRepository;
import com.julio.desafio.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    void criandoTask() {

        TaskRequest requestDTO = new TaskRequest("Teste de Título", "Descrição", Status.TODO, Priority.HIGH, LocalDate.now().plusDays(1), 1L);
        Project project = new Project();
        project.setId(1L);
        Task taskToSave = new Task();
        Task savedTask = new Task();
        savedTask.setId(100L);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(taskMapper.toEntity(requestDTO)).thenReturn(taskToSave);
        when(taskRepository.save(taskToSave)).thenReturn(savedTask);

        Task result = taskService.createTask(requestDTO);

        assertNotNull(result);
        assertEquals(100L, result.getId());

        verify(projectRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(taskToSave);
    }
}
