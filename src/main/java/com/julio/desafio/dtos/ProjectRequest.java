package com.julio.desafio.dtos;

import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public record ProjectRequest(@NotEmpty(message = "Título é obrigatório") String name,
                             String description,
                             Date startDate) {
}
