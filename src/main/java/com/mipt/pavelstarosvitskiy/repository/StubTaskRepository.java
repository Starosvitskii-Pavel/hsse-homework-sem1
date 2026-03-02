package com.mipt.pavelstarosvitskiy.repository;

import com.mipt.pavelstarosvitskiy.model.TaskEntity;

import java.util.List;
import java.util.Optional;

/**
 * Заглушка репозитория, возвращающая фиксированные данные.
 */
public class StubTaskRepository implements TaskRepository {
    @Override
    public TaskEntity save(TaskEntity task) {
        return task;
    }

    @Override
    public Optional<TaskEntity> findById(String id) {
        return Optional.of(new TaskEntity("1", "Stub task", "Description", false));
    }

    @Override
    public List<TaskEntity> findAll() {
        return List.of(new TaskEntity("1", "Stub task 1", "Description 1", false),
                new TaskEntity("2", "Stub task 2", "Description 2", true));
    }

    @Override
    public void deleteById(String id) {

    }
}
