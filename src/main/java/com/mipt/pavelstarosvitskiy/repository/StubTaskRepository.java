package com.mipt.pavelstarosvitskiy.repository;

import com.mipt.pavelstarosvitskiy.model.Task;

import java.util.List;
import java.util.Optional;

/**
 * Заглушка репозитория, возвращающая фиксированные данные.
 */
public class StubTaskRepository implements TaskRepository {
    @Override
    public Task save(Task task) {
        return task;
    }

    @Override
    public Optional<Task> findById(String id) {
    return Optional.of(new Task("1", "Stub task", "Description", false));
    }

    @Override
    public List<Task> findAll() {
        return List.of(new Task("1", "Stub task 1", "Description 1", false),
                new Task("2", "Stub task 2", "Description 2", true));
    }

    @Override
    public void deleteById(String id) {

    }
}
