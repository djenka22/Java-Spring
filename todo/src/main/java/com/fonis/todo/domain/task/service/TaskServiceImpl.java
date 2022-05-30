package com.fonis.todo.domain.task.service;

import com.fonis.todo.domain.task.entity.Task;
import com.fonis.todo.domain.task.models.TaskCreateDto;
import com.fonis.todo.domain.task.repository.TaskRepository;
import com.fonis.todo.infrastructure.mappers.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }



    @Override
    public Task create(TaskCreateDto dto) {
        Task task = taskMapper.taskCreateDtoTask(dto);
        return taskRepository.save(task);
    }

    @Override
    public Task getById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Page<Task> get(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public void delete(Integer id) {

        if(!taskRepository.existsById(id)){
            throw new IllegalArgumentException();
        }
        taskRepository.deleteById(id);

    }

    @Override
    public Task update(Integer id, Task updateTask) {
        Task dbTask = getById(id);
        dbTask.setName(updateTask.getName());
        dbTask.setDescription(updateTask.getDescription());
        dbTask.setDone(updateTask.isDone());
        return taskRepository.save(dbTask);
    }
}
