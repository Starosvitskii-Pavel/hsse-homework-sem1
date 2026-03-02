package com.mipt.pavelstarosvitskiy.repository;

import com.mipt.pavelstarosvitskiy.model.TaskEntity;

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
    TaskEntity save(TaskEntity task);

    /**
     * Найти задачу по ID.
     *
     * @param id идентификатор задачи
     * @return Optional с задачей или пустой Optional, если задача не найдена
     */
    Optional<TaskEntity> findById(String id);

    /**
     * Получить список всех задач.
     *
     * @return список задач
     */
    List<TaskEntity> findAll();

    /**
     * Удалить задачу по ID.
     *
     * @param id идентификатор задачи
     */
    void deleteById(String id);
}
