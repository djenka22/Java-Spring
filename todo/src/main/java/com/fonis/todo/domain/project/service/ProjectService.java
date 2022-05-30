package com.fonis.todo.domain.project.service;

import com.fonis.todo.domain.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {
    Project create(Project project);

    Project getById(Integer id);

    Page<Project> get(Pageable pageable);

    void delete(Integer id);

    Project update(Integer id, Project updateProject);
}
