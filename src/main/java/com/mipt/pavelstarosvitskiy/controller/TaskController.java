package com.mipt.pavelstarosvitskiy.controller;

import com.mipt.pavelstarosvitskiy.model.Task;
import com.mipt.pavelstarosvitskiy.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** REST-контроллер для управления задачами. Предоставляет API для операций CRUD. */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  /**
   * POST /api/tasks Создать новую задачу. Принимает JSON с задачей, возвращает созданную задачу.
   */
  public Task createTask(@RequestBody Task task) {
    return taskService.saveTask(task);
  }

    /**
     * GET /api/tasks/{id}
     * Получить задачу по ID.
     * Возвращает 200 OK и задачу, либо 404 Not Found.
     */
    @GetMapping("/{id}")
  public ResponseEntity<Task> getTaskById(@PathVariable String id) {
    return taskService
        .getTaskById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

    /**
     * GET /api/tasks
     * Получить список всех задач.
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    /**
     * PUT /api/tasks/{id}
     * Обновить существующую задачу.
     * Если задачи нет, она будет создана (в данной реализации InMemory репозитория).
     */
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task task) {
        task.setId(id);
        return taskService.saveTask(task);
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
