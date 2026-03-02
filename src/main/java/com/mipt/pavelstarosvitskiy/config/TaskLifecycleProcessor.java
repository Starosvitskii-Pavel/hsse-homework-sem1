package com.mipt.pavelstarosvitskiy.config;

import com.mipt.pavelstarosvitskiy.repository.TaskRepository;
import com.mipt.pavelstarosvitskiy.service.TaskService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class TaskLifecycleProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TaskService || bean instanceof TaskRepository) {
            System.out.println(">>> BPP BeforeInit: " + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TaskService || bean instanceof TaskRepository) {
            System.out.println(">>> BPP AfterInit: " + beanName);
        }
        return bean;
    }
}
