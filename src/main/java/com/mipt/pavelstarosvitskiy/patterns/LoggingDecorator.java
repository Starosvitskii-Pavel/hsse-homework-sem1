package com.mipt.pavelstarosvitskiy.patterns;

import java.util.Optional;

public class LoggingDecorator implements DataService {
    private final DataService original;

    public LoggingDecorator(DataService original) {
        this.original = original;
    }

    @Override
    public Optional<String> findDataByKey(String key) {
        Optional<String> data = original.findDataByKey(key);
        if (data.isPresent())
            System.out.println("Value found: " + data + "by key: " + key);
        else
            System.out.println("Value not found by key:" + key);

        return data;
    }

    @Override
    public void saveData(String key, String data) {
        original.saveData(key, data);
        System.out.println("Data: " + data + "was written with key:" + key + "successfully.");
    }

    @Override
    public boolean deleteData(String key) {
        boolean isDeleted = original.deleteData(key);

        if (isDeleted) System.out.println("Data with key: " + key + "was successfully deleted.");
        else System.out.println("Nothing to delete - data was already null!");

        return isDeleted;
    }
}
