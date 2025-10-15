package com.julio.desafio.dtos;

import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public record ProjectResponse(String name,
                              String description,
                              Date startDate) {
}
