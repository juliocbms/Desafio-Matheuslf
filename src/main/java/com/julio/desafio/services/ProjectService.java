package com.julio.desafio.services;


import com.julio.desafio.entity.Project;
import com.julio.desafio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public Project createProject(Project newproject){
        return  projectRepository.save(newproject);
    }

    @Transactional(readOnly = true)
    public Page<Project> listOfProjects(Pageable pageable){
        return projectRepository.findAll(pageable);
    }

}
