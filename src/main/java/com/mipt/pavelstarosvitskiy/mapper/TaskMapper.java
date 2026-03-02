package com.mipt.pavelstarosvitskiy.mapper;

import com.mipt.pavelstarosvitskiy.dto.TaskDto;
import com.mipt.pavelstarosvitskiy.model.TaskEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {

    /**
     * Преобразует сущность (Task) в DTO (TaskDto).
     */
    public TaskDto toDto(TaskEntity task) {
        if (task == null) {
            return null;
        }
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCompleted()
        );
    }

    /**
     * Преобразует DTO (TaskDto) в сущность (TaskEntity).
     */
    public TaskEntity toEntity(TaskDto dto) {
        if (dto == null) {
            return null;
        }
        return new TaskEntity(
                dto.id(),
                dto.title(),
                dto.description(),
                dto.completed()
        );
    }

    /**
     * Преобразует список сущностей в список DTO.
     */
    public List<TaskDto> toDtoList(List<TaskEntity> tasks) {
        return tasks.stream()
                .map(this::toDto)
                .toList();
    }
}
