package com.fonis.todo.domain.task.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record TaskCreateDto(
        @NotBlank(message = "Name is required field") //ne moze biti null ni prazan string
        @Size(min = 3, message = "Name must have at least 3 characters")
         String name,
         String description,
         @NotNull(message = "Project id is required field")
         Integer projectId,
         boolean done
) {




}
