package com.julio.desafio.controller;

import com.julio.desafio.dtos.*;
import com.julio.desafio.entity.Project;
import com.julio.desafio.entity.User;
import com.julio.desafio.repository.UserRepository;
import com.julio.desafio.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        return null;
    }

    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request){
       User userToSave = userMapper.toEntity(request);
       User savedUser = userService.createUser(userToSave);
       LoginResponse response = userMapper.toResponse(savedUser);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest projectRequest){
        Project projectToSave = projectMapper.toEntity(projectRequest);
        Project savedProject = projectService.createProject(projectToSave);
        ProjectResponse response = projectMapper.toResponse(savedProject);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
