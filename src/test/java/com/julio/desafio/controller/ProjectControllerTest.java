package com.julio.desafio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julio.desafio.dtos.ProjectRequest;
import com.julio.desafio.dtos.ProjectResponse;
import com.julio.desafio.entity.Project;
import com.julio.desafio.mapper.ProjectMapper;
import com.julio.desafio.services.ProjectService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProjectService projectService;

    @MockBean
    private ProjectMapper projectMapper;

    @TestConfiguration
    static class TestConfig {

        @Bean
        @Primary
        public ProjectService projectService() {
            return Mockito.mock(ProjectService.class);
        }

        @Bean
        @Primary
        public ProjectMapper projectMapper() {
            return Mockito.mock(ProjectMapper.class);
        }
    }

    @Test
    void criandoProjeto_ComDadosValidos_DeveRetornarCriado() throws Exception {

        ProjectRequest requestDTO = new ProjectRequest("Novo Projeto", "Descrição", LocalDate.now());
        ProjectResponse responseDTO = new ProjectResponse(1L, "Novo Projeto", "Descrição", LocalDate.now());
        String requestJson = objectMapper.writeValueAsString(requestDTO);

        when(projectMapper.toEntity(any(ProjectRequest.class))).thenReturn(new Project());
        when(projectService.createProject(any(Project.class))).thenReturn(new Project());
        when(projectMapper.toResponse(any(Project.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Novo Projeto"));
    }
}