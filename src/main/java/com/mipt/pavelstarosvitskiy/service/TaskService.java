package com.mipt.pavelstarosvitskiy.service;

import com.mipt.pavelstarosvitskiy.model.Task;
import com.mipt.pavelstarosvitskiy.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Основной сервис для работы с задачами.
 * Реализует бизнес-логику и делегирует запросы к репозиторию.
 */
@Service
public class TaskService {
    private final TaskRepository repository;

    /**
     * Конструктор для внедрения зависимости.
     * @param repository репозиторий задач
     */
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Получить все задачи.
     */
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    /**
     * Получить задачу по ID.
     */
    public Optional<Task> getTaskById(String id) {
        return repository.findById(id);
    }

    /**
     * Создать или обновить задачу.
     */
    public Task saveTask(Task task) {
        return repository.save(task);
    }

    /**
     * Удалить задачу по ID.
     */
    public void deleteTask(String id) {
        repository.deleteById(id);
    }
}
