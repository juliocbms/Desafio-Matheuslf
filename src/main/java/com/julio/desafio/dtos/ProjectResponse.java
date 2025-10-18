package com.julio.desafio.dtos;



import java.time.LocalDate;
import java.util.Date;

public record ProjectResponse(Long id,
                              String name,
                              String description,
                              LocalDate startDate) {
}
