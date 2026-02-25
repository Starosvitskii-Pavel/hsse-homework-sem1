package com.mipt.pavelstarosvitskiy.repository;

import com.mipt.pavelstarosvitskiy.model.Task;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс репозитория для управления задачами.
 * Определяет контракт для операций CRUD (Create, Read, Update, Delete).
 */
public interface TaskRepository {
    /**
     * Сохранить (создать или обновить) задачу.
     *
     * @param task задача для сохранения
     * @return сохраненная задача
     */
    Task save(Task task);

    /**
     * Найти задачу по ID.
     *
     * @param id идентификатор задачи
     * @return Optional с задачей или пустой Optional, если задача не найдена
     */
    Optional<Task> findById(String id);

    /**
     * Получить список всех задач.
     *
     * @return список задач
     */
    List<Task> findAll();

    /**
     * Удалить задачу по ID.
     *
     * @param id идентификатор задачи
     */
    void deleteById(String id);
}
