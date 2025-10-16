package com.julio.desafio.mapper;

import com.julio.desafio.dtos.ProjectRequest;
import com.julio.desafio.dtos.ProjectResponse;
import com.julio.desafio.entity.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project toEntity(ProjectRequest request);
    ProjectResponse toResponse(Project project);
}
