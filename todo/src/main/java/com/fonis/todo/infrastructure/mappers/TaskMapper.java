package com.fonis.todo.infrastructure.mappers;

import com.fonis.todo.domain.project.service.ProjectService;
import com.fonis.todo.domain.task.entity.Task;
import com.fonis.todo.domain.task.models.TaskCreateDto;
import org.mapstruct.Mapper; //vidi video od 15min na yt o mapstruct
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {ProjectService.class})
public interface TaskMapper {

    @Mapping(source = "projectId", target = "project")
    Task taskCreateDtoTask(TaskCreateDto dto);





}
