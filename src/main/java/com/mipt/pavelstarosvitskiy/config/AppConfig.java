package com.mipt.pavelstarosvitskiy.config;

import com.mipt.pavelstarosvitskiy.repository.StubTaskRepository;
import com.mipt.pavelstarosvitskiy.repository.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс конфигурации приложения. Здесь определяются бины, которые требуют ручной настройки или не
 * могут быть помечены стереотипными аннотациями.
 */
@Configuration
public class AppConfig {

    @Bean
    public TaskRepository stubTaskRepository() {
        return new StubTaskRepository();
    }
}
