package com.julio.desafio.dtos;

import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public record ProjectResponse(Long id,
                              String name,
                              String description,
                              Date startDate) {
}
