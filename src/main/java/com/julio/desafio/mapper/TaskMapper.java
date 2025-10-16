package com.julio.desafio.mapper;

import com.julio.desafio.dtos.TaskRequest;
import com.julio.desafio.dtos.TaskResponse;
import com.julio.desafio.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toEntity(TaskRequest request);
    TaskResponse toResponse(Task task);
}
