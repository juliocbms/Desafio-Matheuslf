package com.julio.desafio.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record ProjectRequest(@NotBlank(message = "Título é obrigatório")
                             @Size(min = 3, max = 100, message = " O nome deve ter de 3 a 100 caracteres")String name,
                             String description,
                             Date startDate) {
}
