package com.julio.desafio.controller;

import com.julio.desafio.dtos.ProjectRequest;
import com.julio.desafio.dtos.ProjectResponse;
import com.julio.desafio.entity.Project;
import com.julio.desafio.mapper.ProjectMapper;
import com.julio.desafio.services.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectMapper projectMapper;


    @Operation(summary = "Create some project")
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest projectRequest){
        Project projectToSave = projectMapper.toEntity(projectRequest);
        Project savedProject = projectService.createProject(projectToSave);
        ProjectResponse response = projectMapper.toResponse(savedProject);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "List of projects")
    @GetMapping
    public ResponseEntity<List<Project>> listOfProject(){
        List<Project> projects = projectService.listOfProjects();
        return ResponseEntity.ok(projects);
    }

}
