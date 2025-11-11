package com.julio.desafio.services;


import com.julio.desafio.controller.AuthController;
import com.julio.desafio.dtos.RegisterUserRequest;
import com.julio.desafio.entity.User;
import com.julio.desafio.enums.Role;
import com.julio.desafio.mapper.UserMapper;
import com.julio.desafio.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(UserService.class.getName());

    public User createUser(RegisterUserRequest request) {

        logger.info("Trying register user");

        logger.error("Error founded");

        User newUser = userMapper.toEntity(request);

        try {
            if (request.role() != null) {
                newUser.setRoles(Set.of(request.role()));
            } else {
                newUser.setRoles(Set.of(Role.ROLE_USER));
            }
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

            return repository.save(newUser);
        }
        catch (IllegalArgumentException e){
            logger.warn("Invalid argument while registering user: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while registering user", e);
            throw e;
        }
    }
}
