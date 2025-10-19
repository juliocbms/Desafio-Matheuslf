package com.julio.desafio.mapper;

import com.julio.desafio.dtos.LoginResponse;
import com.julio.desafio.dtos.RegisterUserRequest;
import com.julio.desafio.dtos.RegisterUserResponse;
import com.julio.desafio.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "senha", target = "password")
    @Mapping(target = "id", ignore = true)
    User toEntity(RegisterUserRequest request);
    RegisterUserResponse toRegisterResponse(User user);
}
