package com.fonis.todo.domain.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fonis.todo.domain.task.entity.Task;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is required field") //ne moze biti null ni prazan string
    @Size(min = 3, message = "Name must have at least 3 characters")
    private String name;


    @OneToMany(mappedBy = "project",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    @JsonManagedReference
    private Set<Task> tasks;

    public Project(){

    }

    public Project(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
