package com.mipt.pavelstarosvitskiy.patterns;

import java.util.Optional;

public class ValidationDecorator implements DataService{
    private final DataService original;

    public ValidationDecorator(DataService original) {
        this.original = original;
    }

    @Override
    public Optional<String> findDataByKey(String key) {
        Optional<String> data = original.findDataByKey(key);
        if (!data.isPresent()) {
            System.out.println("Data should not be null!");
            throw new NullPointerException();
        }
        else if (data.get().isEmpty()) System.out.println("String should not be empty!");

        return data;
    }

    @Override
    public void saveData(String key, String data) {
        if (data == null) {
            System.out.println("Data should not be null!");
            throw new NullPointerException();
        }
        else if (data.isEmpty()) System.out.println("String should not be empty!");

        original.saveData(key, data);
    }

    @Override
    public boolean deleteData(String key) {
        if (key == null) {
            System.out.println("Key should not be null!");
            throw new NullPointerException();
        }

        return original.deleteData(key);
    }
}
