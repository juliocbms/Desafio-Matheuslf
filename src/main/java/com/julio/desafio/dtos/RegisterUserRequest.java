package com.julio.desafio.dtos;

import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequest(@NotBlank(message = "Nome é obrigatóri")String name,
                                  @NotBlank(message = "Email é obrigatório")String email,
                                  @NotBlank(message = "Senha é obrigatória")String senha) {
}
