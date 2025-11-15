package com.julio.desafio.controller;

import com.julio.desafio.controller.Assembler.ProjectModelAssembler;
import com.julio.desafio.dtos.ProjectRequest;
import com.julio.desafio.dtos.ProjectResponse;
import com.julio.desafio.entity.Project;
import com.julio.desafio.mapper.ProjectMapper;
import com.julio.desafio.services.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/projects")
@Tag(name = "Projetos", description = "Endpoints para criar e gerenciar projetos")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private  ProjectModelAssembler projectAssembler;
    @Autowired
    private  PagedResourcesAssembler<Project> pagedAssembler;

    @Operation(
            summary = "Cria um novo projeto", description = "Registra um novo projeto no banco de dados com base nos dados fornecidos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Projeto criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjectResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest projectRequest){
        Project projectToSave = projectMapper.toEntity(projectRequest);
        Project savedProject = projectService.createProject(projectToSave);
        ProjectResponse response = projectMapper.toResponse(savedProject);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Lista todos os projetos", description = "Retorna uma lista com todos os projetos cadastrados no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de projetos retornada com sucesso")
    })
    @GetMapping(value = "/admin", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PagedModel<EntityModel<ProjectResponse>>> listOfProject(Pageable pageable){
        Page<Project> projectsPage = projectService.listOfProjects(pageable);
        PagedModel<EntityModel<ProjectResponse>> pagedModel = pagedAssembler.toModel(
                projectsPage,
                project -> projectAssembler.toModel(projectMapper.toResponse(project))
        );
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Project>> findById(@PathVariable Long id){
        Optional<Project> response = projectService.findById(id);
        return ResponseEntity.ok(response);

    }
}