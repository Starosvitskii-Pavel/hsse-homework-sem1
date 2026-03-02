package com.mipt.pavelstarosvitskiy.service;

import com.mipt.pavelstarosvitskiy.model.TaskEntity;
import com.mipt.pavelstarosvitskiy.repository.TaskRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Основной сервис для работы с задачами.
 * Реализует бизнес-логику и делегирует запросы к репозиторию.
 */
@Service
public class TaskService {
    private final TaskRepository repository;

    private final Map<String, TaskEntity> taskCache = new ConcurrentHashMap<>();

    @Value("${spring.application.name}")
    private String name;

    @Value("${app.version}")
    private String appVersion;

    /**
     * Конструктор для внедрения зависимости.
     *
     * @param repository репозиторий задач
     */
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Загрузить данные в кэш
     */
    @PostConstruct
    public void init() {
        System.out.println(">>> ЗАПУСК ПРИЛОЖЕНИЯ: " + name + " (v" + appVersion + ")");
        System.out.println(">>> @PostConstruct: Данные загружаются в кэш...");
        TaskEntity demoTask = new TaskEntity("1", "Изучить Spring", "Не зря же Сурен и Бобряковы объясняли", false);
        repository.save(demoTask);
        taskCache.put(demoTask.getId(), demoTask);
        System.out.println(">>> @PostConstruct: Кэш инициализирован. Задач в кэше: " + taskCache.size());
    }

    /**
     * Очистить ресурсы и сохранить состояние
     */
    @PreDestroy
    public void cleanup() {
        System.out.println(">>> @PreDestroy: Очистка ресурсов...");
        System.out.println(">>> @PreDestroy: Сохранение состояния кэша (имитация). Задач было: " + taskCache.size());
        taskCache.clear();
    }

    /**
     * Получить все задачи.
     */
    public List<TaskEntity> getAllTasks() {
        return repository.findAll();
    }

    /**
     * Получить задачу по ID.
     */
    public Optional<TaskEntity> getTaskById(String id) {
        if (taskCache.containsKey(id)) {
            System.out.println(">>> Взято из кэша: " + id);
            return Optional.of(taskCache.get(id));
        }
        return repository.findById(id);
    }

    /**
     * Создать или обновить задачу.
     */
    public TaskEntity saveTask(TaskEntity task) {
        TaskEntity saved = repository.save(task);
        taskCache.put(saved.getId(), saved);
        return saved;
    }

    /**
     * Удалить задачу по ID.
     */
    public void deleteTask(String id) {
        repository.deleteById(id);
        taskCache.remove(id);
    }
}
