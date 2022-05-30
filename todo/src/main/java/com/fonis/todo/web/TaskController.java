package com.fonis.todo.web;

import com.fonis.todo.domain.project.entity.Project;
import com.fonis.todo.domain.task.entity.Task;
import com.fonis.todo.domain.task.models.TaskCreateDto;
import com.fonis.todo.domain.task.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody TaskCreateDto task) {
        Task createdTask = taskService.create(task);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Integer id){
        Task task = taskService.getById(id);
        return ResponseEntity.ok(task) ;
    }

    @GetMapping
    public ResponseEntity<Page<Task>> getAll(Pageable pageable){
        Page<Task> page = taskService.get(pageable);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        taskService.delete(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> update (@RequestBody Task updateTask, @PathVariable Integer id){
        Task task = taskService.update(id, updateTask);
        return ResponseEntity.ok(task);
    }
}
