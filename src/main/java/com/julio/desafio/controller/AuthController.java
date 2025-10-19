package com.julio.desafio.controller;

import com.julio.desafio.dtos.*;
import com.julio.desafio.entity.Project;
import com.julio.desafio.entity.User;
import com.julio.desafio.mapper.UserMapper;
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

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request){
       User userToSave = userMapper.toEntity(request);
       User savedUser = userService.createUser(userToSave);
       RegisterUserResponse response = userMapper.toRegisterResponse(savedUser);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }




}
