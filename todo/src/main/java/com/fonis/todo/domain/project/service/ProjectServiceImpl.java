package com.fonis.todo.domain.project.service;

import com.fonis.todo.domain.project.entity.Project;
import com.fonis.todo.domain.project.repository.ProjectRepository;
import com.fonis.todo.infrastructure.exceptions.custom.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getById(Integer id) {
        return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
    }

    @Override
    public Page<Project> get(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public void delete(Integer id) {
        if(!projectRepository.existsById(id)){
            throw new IllegalArgumentException();
        }

        projectRepository.deleteById(id);
    }

    @Override
    public Project update(Integer id, Project updateProject) {
        Project project = getById(id);
        project.setName(updateProject.getName());
        return projectRepository.save(project);
    }


}
