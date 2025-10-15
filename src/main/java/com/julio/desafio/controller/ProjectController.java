package com.julio.desafio.controller;

import com.julio.desafio.entity.Project;
import com.julio.desafio.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Project> createProject(@RequestBody Project newproject){
        Project project = projectService.createProject(newproject);
        return ResponseEntity.ok(project);
    }

}
