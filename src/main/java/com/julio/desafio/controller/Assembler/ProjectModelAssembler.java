package com.julio.desafio.controller.Assembler;

import com.julio.desafio.controller.ProjectController;
import com.julio.desafio.controller.TaskController;
import com.julio.desafio.dtos.ProjectResponse;
import com.julio.desafio.entity.Task;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjectModelAssembler implements RepresentationModelAssembler<ProjectResponse, EntityModel<ProjectResponse>> {
    @Override
    public EntityModel<ProjectResponse> toModel(ProjectResponse entity) {
        var selfLink = linkTo(
                WebMvcLinkBuilder.methodOn(ProjectController.class).findById(entity.id())
        ).withSelfRel();
        return EntityModel.of(entity, selfLink);
    }

    @Override
    public CollectionModel<EntityModel<ProjectResponse>> toCollectionModel(Iterable<? extends ProjectResponse> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
