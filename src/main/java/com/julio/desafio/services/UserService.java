package com.julio.desafio.services;


import com.julio.desafio.dtos.RegisterUserRequest;
import com.julio.desafio.entity.User;
import com.julio.desafio.enums.Role;
import com.julio.desafio.mapper.UserMapper;
import com.julio.desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public User createUser(RegisterUserRequest request) {

        User newUser = userMapper.toEntity(request);

        if (request.role() != null) {
            newUser.setRoles(Set.of(request.role()));
        } else {
            newUser.setRoles(Set.of(Role.ROLE_USER));
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return repository.save(newUser);
    }
}
