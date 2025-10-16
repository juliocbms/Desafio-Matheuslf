package com.julio.desafio.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public record ProjectRequest(@NotBlank(message = "Título é obrigatório") String name,
                             String description,
                             Date startDate) {
}
