package com.julio.desafio.services;

import com.julio.desafio.dtos.RegisterUserRequest;
import com.julio.desafio.entity.Task;
import com.julio.desafio.entity.User;
import com.julio.desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User createUser(User newUser){
        return repository.save(newUser);
    }
}
