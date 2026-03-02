package com.mipt.pavelstarosvitskiy.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeScopedBean {
    private final String instanceId;

    public PrototypeScopedBean() {
        this.instanceId = UUID.randomUUID().toString();
        System.out.println(">>> Создан PrototypeScopedBean: " + instanceId);
    }

    public String getInstanceId() {
        return instanceId;
    }
}
