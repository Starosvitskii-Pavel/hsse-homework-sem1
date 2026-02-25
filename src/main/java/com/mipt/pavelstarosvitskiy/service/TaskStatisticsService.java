package com.mipt.pavelstarosvitskiy.service;

import com.mipt.pavelstarosvitskiy.model.Task;
import com.mipt.pavelstarosvitskiy.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/** Сервис статистики (демонстрационный). */
@Service
public class TaskStatisticsService {
  private final TaskRepository primaryRepository;
  private final TaskRepository stubRepository;

  /**
   * Внедрение двух разных репозиториев одного интерфейса.
   *
   * @param primaryRepository внедрится InMemoryTaskRepository
   * @param stubRepository внедрится StubTaskRepository
   */
  public TaskStatisticsService(
      TaskRepository primaryRepository,
      @Qualifier("stubTaskRepository") TaskRepository stubRepository) {
    this.primaryRepository = primaryRepository;
    this.stubRepository = stubRepository;
  }

    /**
     * Метод для сравнения количества задач в разных репозиториях.
     */
    public void printComparison() {
        List<Task> primaryTasks = primaryRepository.findAll();
        List<Task> stubTasks = stubRepository.findAll();

        System.out.println("--- Статистика репозиториев ---");
        System.out.println("Основной (InMemory) содержит задач: " + primaryTasks.size());
        System.out.println("Заглушка (Stub) содержит задач: " + stubTasks.size());

        if (!stubTasks.isEmpty()) {
            System.out.println("Пример из Stub: " + stubTasks.get(0).getTitle());
        }
        System.out.println("--------------------------------");
    }
}
