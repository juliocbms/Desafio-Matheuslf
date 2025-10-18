package com.julio.desafio.services;

import com.julio.desafio.dtos.TaskRequest;
import com.julio.desafio.dtos.TaskUpdateRequest;
import com.julio.desafio.entity.Project;
import com.julio.desafio.entity.Task;
import com.julio.desafio.mapper.TaskMapper;
import com.julio.desafio.repository.ProjectRepository;
import com.julio.desafio.repository.TaskRepository;
import com.julio.desafio.services.exceptions.DatabaseException;
import com.julio.desafio.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskMapper taskMapper;


    public Task createTask(TaskRequest dto){
        Project project = projectRepository.findById(dto.projectId())
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado com o ID: " + dto.projectId()));
        Task newTask = taskMapper.toEntity(dto);
        newTask.setProject(project);

        return taskRepository.save(newTask);
    }

    public Task updateTask(Long id, TaskUpdateRequest dto){
            Task taskToUpdate = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            taskToUpdate.setStatus(dto.status());
            return taskRepository.save(taskToUpdate);
    }

    public void deleteTask(Long id){
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        try{
            taskRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

}
