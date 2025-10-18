package com.julio.desafio.mapper;

import com.julio.desafio.dtos.TaskRequest;
import com.julio.desafio.dtos.TaskResponse;
import com.julio.desafio.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    Task toEntity(TaskRequest request);

    @Mapping(source = "project.id", target = "projectId")
    TaskResponse toResponse(Task task);

}
