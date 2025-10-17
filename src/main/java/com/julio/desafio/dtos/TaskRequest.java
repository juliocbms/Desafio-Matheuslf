package com.julio.desafio.dtos;

import com.julio.desafio.entity.Project;
import com.julio.desafio.enums.Priority;
import com.julio.desafio.enums.Status;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record TaskRequest(@NotBlank(message = "Título é obrigatório") @Size(min = 5, max = 150, message = " O nome deve ter de 5 a 150 caracteres")String title,
                          String description,
                          @NotNull(message = "O status é obrigatório") Status status,
                          @NotNull(message = "Prioridade é obrigatória") Priority priority,
                          @NotNull(message = "Data Limite é obrigatória") @Future(message = "A data de vencimento deve ser no futuro") Date dueDate,
                          @NotNull(message = "Projeto é obrigatório")Long projectId) {
}
