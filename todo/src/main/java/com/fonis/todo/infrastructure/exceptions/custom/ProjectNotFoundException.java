package com.fonis.todo.infrastructure.exceptions.custom;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(Integer id) {
        super("Project with id " + id + " not found");
    }
}
