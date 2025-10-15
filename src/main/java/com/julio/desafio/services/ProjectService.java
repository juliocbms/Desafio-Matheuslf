package com.julio.desafio.services;

import com.julio.desafio.dtos.ProjectRequest;
import com.julio.desafio.entity.Project;
import com.julio.desafio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    public Project createProject(Project newproject){
        return   projectRepository.save(newproject);
    }

    public List<Project> listOfProjects(){
        return projectRepository.findAll();
    }

}
