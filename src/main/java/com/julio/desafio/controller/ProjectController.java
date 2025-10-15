package com.julio.desafio.controller;

import com.julio.desafio.dtos.ProjectRequest;
import com.julio.desafio.dtos.ProjectResponse;
import com.julio.desafio.entity.Project;
import com.julio.desafio.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest projectRequest){
        Project project = new Project();
        project.setName(projectRequest.name());
        project.setDescription(projectRequest.description());
        project.setStartDate(projectRequest.startDate());
        projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProjectResponse(project.getName(), project.getDescription(), project.getStartDate()));
    }

}
