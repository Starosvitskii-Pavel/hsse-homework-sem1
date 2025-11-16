package com.mipt.pavelstarosvitskiy.patterns;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CachingDecorator implements DataService {
    private final DataService original;
    private Map<String, String> cache = new HashMap<>();

    public CachingDecorator(DataService original) {
        this.original = original;
    }

    @Override
    public Optional<String> findDataByKey(String key) {
        if (!cache.containsKey(key))
            if (original.findDataByKey(key).isPresent())
                cache.put(key, original.findDataByKey(key).get());

        return Optional.of(cache.get(key));
    }

    @Override
    public void saveData(String key, String data) {
        cache.put(key, data);
        original.saveData(key, data);
    }

    @Override
    public boolean deleteData(String key) {
        cache.remove(key);
        return original.deleteData(key);
    }
}

