package com.julio.desafio.dtos;



import java.util.Date;

public record ProjectResponse(Long id,
                              String name,
                              String description,
                              Date startDate) {
}
