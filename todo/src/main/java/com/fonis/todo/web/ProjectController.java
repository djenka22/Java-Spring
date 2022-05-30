package com.fonis.todo.web;

import com.fonis.todo.domain.project.entity.Project;
import com.fonis.todo.domain.project.service.ProjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/projects")
@Tag(description = "Project resource that provides access to projects",
name = "Project Resource")
public class ProjectController {


    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(summary = "Create project",
            description = "Resource to create project")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "User created sucessfully")})
    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        Project createdProject = projectService.create(project);
        return ResponseEntity.ok(createdProject);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable Integer id){
        Project project = projectService.getById(id);
        return ResponseEntity.ok(project);
    }

    @GetMapping
    public ResponseEntity<Page<Project>> getAll(Pageable pageable){
        Page<Project> page = projectService.get(pageable);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        projectService.delete(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Project> update (@RequestBody Project updateProject, @PathVariable Integer id){
        Project project = projectService.update(id, updateProject);
        return ResponseEntity.ok(project);
    }
}
