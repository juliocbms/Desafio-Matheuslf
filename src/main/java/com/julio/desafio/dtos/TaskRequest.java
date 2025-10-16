package com.julio.desafio.dtos;

import com.julio.desafio.entity.Project;
import com.julio.desafio.enums.Priority;
import com.julio.desafio.enums.Status;

import java.util.Date;

public record TaskRequest(String title,
                          String description,
                          Status status,
                          Priority priority,
                          Date dueDate,
                          Project projectId) {
}
