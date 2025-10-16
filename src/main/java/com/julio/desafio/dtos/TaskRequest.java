package com.julio.desafio.dtos;

import com.julio.desafio.entity.Project;
import com.julio.desafio.enums.Priority;
import com.julio.desafio.enums.Status;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record TaskRequest(@NotBlank(message = "Título é obrigatório") String title,
                          String description,
                         @NotBlank(message = "Status é obrigatório") Status status,
                          @NotBlank(message = "Prioridade é obrigatória") Priority priority,
                          @NotBlank(message = "Data Limite é obrigatória") Date dueDate,
                          @NotBlank(message = "Projeto é obrigatório")Project projectId) {
}
