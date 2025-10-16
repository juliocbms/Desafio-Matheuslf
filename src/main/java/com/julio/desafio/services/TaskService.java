package com.julio.desafio.services;

import com.julio.desafio.entity.Task;
import com.julio.desafio.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    public Task createTask(Task newTask){
       return taskRepository.save(newTask);
    }
}
