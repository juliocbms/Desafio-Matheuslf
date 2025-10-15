package com.julio.desafio.services;

import com.julio.desafio.entity.Project;
import com.julio.desafio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private Project project;


    public void createProject(Project newproject){
        projectRepository.save(newproject);
    }

}
