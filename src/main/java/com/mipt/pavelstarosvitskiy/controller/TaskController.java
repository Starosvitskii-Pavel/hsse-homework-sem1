package com.mipt.pavelstarosvitskiy.controller;

import com.mipt.pavelstarosvitskiy.dto.TaskDto;
import com.mipt.pavelstarosvitskiy.mapper.TaskMapper;
import com.mipt.pavelstarosvitskiy.model.TaskEntity;
import com.mipt.pavelstarosvitskiy.service.PrototypeScopedBean;
import com.mipt.pavelstarosvitskiy.service.RequestScopedBean;
import com.mipt.pavelstarosvitskiy.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST-контроллер для управления задачами. Предоставляет API для операций CRUD.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    private final RequestScopedBean requestScopedBean;
    private final PrototypeScopedBean prototypeScopedBean;


    public TaskController(TaskService taskService, TaskMapper taskMapper, RequestScopedBean requestScopedBean, PrototypeScopedBean prototypeScopedBean) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;

        this.requestScopedBean = requestScopedBean;
        this.prototypeScopedBean = prototypeScopedBean;

        System.out.println(">>> Контроллер создан. Prototype ID: " + prototypeScopedBean.getInstanceId());
    }

    /**
     * POST /api/tasks Создать новую задачу. Принимает JSON с задачей, возвращает созданную задачу.
     */
    @PostMapping
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        TaskEntity entity = taskMapper.toEntity(taskDto);

        TaskEntity savedEntity = taskService.saveTask(entity);

        return taskMapper.toDto(savedEntity);
    }

    /**
     * GET /api/tasks/{id}
     * Получить задачу по ID.
     * Возвращает 200 OK и задачу, либо 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id).map(taskMapper::toDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET /api/tasks
     * Получить список всех задач.
     */
    @GetMapping
    public List<TaskDto> getAllTasks() {
        System.out.println("Обработка запроса: " + requestScopedBean.getRequestId());
        System.out.println("Prototype внутри контроллера: " + prototypeScopedBean.getInstanceId());

        return taskMapper.toDtoList(taskService.getAllTasks());
    }

    /**
     * PUT /api/tasks/{id}
     * Обновить существующую задачу.
     * Если задачи нет, она будет создана (в данной реализации InMemory репозитория).
     */
    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable String id, @RequestBody TaskDto taskDto) {
        TaskEntity entity = taskMapper.toEntity(taskDto);
        entity.setId(id);

        TaskEntity updatedEntity = taskService.saveTask(entity);
        return taskMapper.toDto(updatedEntity);
    }

    /**
     * DELETE /api/tasks/{id}
     * Удалить задачу по ID.
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }
}
