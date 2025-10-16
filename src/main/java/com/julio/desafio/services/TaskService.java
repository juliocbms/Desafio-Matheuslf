package com.julio.desafio.services;

import com.julio.desafio.dtos.TaskRequest;
import com.julio.desafio.dtos.TaskUpdateRequest;
import com.julio.desafio.entity.Project;
import com.julio.desafio.entity.Task;
import com.julio.desafio.mapper.TaskMapper;
import com.julio.desafio.repository.ProjectRepository;
import com.julio.desafio.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        newTask.setProjectId(project);

        return taskRepository.save(newTask);
    }

public Task updateTask(Long id, TaskUpdateRequest dto){
        taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task não encontrada com o ID: " + id));
        Task newTask = taskMapper.updateToEntity(dto);
        newTask.setId(id);
        newTask.setStatus(dto.status());
        return taskRepository.save(newTask);
}

}
