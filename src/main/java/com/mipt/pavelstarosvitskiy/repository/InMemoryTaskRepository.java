package com.mipt.pavelstarosvitskiy.repository;

import com.mipt.pavelstarosvitskiy.model.TaskEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Реализация репозитория, хранящая задачи в оперативной памяти (в Map).
 */
@Repository
@Primary
public class InMemoryTaskRepository implements TaskRepository {
    private final Map<String, TaskEntity> tasks = new ConcurrentHashMap<>();

    @Override
    public TaskEntity save(TaskEntity task) {
        if (task.getId() == null) {
            task.setId(UUID.randomUUID().toString());
        }

        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Optional<TaskEntity> findById(String id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public List<TaskEntity> findAll() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void deleteById(String id) {
        tasks.remove(id);
    }
}
