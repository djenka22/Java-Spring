package com.fonis.todo.domain.task.service;

import com.fonis.todo.domain.task.entity.Task;
import com.fonis.todo.domain.task.models.TaskCreateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {


    Task create(TaskCreateDto dto);

    Task getById(Integer id);

    Page<Task> get(Pageable pageable);

    void delete(Integer id);

    Task update(Integer id, Task updateTask);
}
