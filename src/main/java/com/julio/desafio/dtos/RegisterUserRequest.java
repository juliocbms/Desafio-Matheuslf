package com.julio.desafio.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.julio.desafio.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@JsonPropertyOrder({"nome_aqui","email","senha","role"})
public record RegisterUserRequest(@NotBlank(message = "Nome é obrigatóri") @JsonProperty("nome_aqui") String name,
                                  @NotBlank(message = "Email é obrigatório") @Email String email,
                                  @NotBlank(message = "Senha é obrigatória")String senha,
                                  Role role) {
}
