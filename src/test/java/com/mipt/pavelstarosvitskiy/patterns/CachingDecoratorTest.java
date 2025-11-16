package com.mipt.pavelstarosvitskiy.patterns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

class CachingDecoratorTest {
    DataService simpleDataService;
    DataService cachedDataService;

    @BeforeEach
    void setUp() {
        simpleDataService = new SimpleDataService();
        cachedDataService = new CachingDecorator(simpleDataService);
    }

    @Test
    void shouldKeepDataInCacheAfterFindingData() {
        simpleDataService.saveData("key", "data");
        Optional<String> data1 = cachedDataService.findDataByKey("key");

        assertEquals("data", data1.orElse(""));

        simpleDataService.deleteData("key");

        Optional<String> data2 = cachedDataService.findDataByKey("key");
        assertNotNull(data2);
        assertEquals("data", data2.orElse(""));
    }

    @Test
    void shouldKeepDataInCacheAfterSavingData() {
        cachedDataService.saveData("key", "data");
        Optional<String> data1 = cachedDataService.findDataByKey("key");

        assertEquals("data", data1.orElse(""));

        simpleDataService.deleteData("key");

        Optional<String> data2 = cachedDataService.findDataByKey("key");
        assertNotNull(data2);
        assertEquals("data", data2.orElse(""));
    }

    @Test
    void shouldDeleteData() {
        cachedDataService.saveData("key", "data");
        assertEquals("data", cachedDataService.findDataByKey("key").orElse(""));

        cachedDataService.deleteData("key");
        assertThrows(NullPointerException.class, () -> cachedDataService.findDataByKey("key"));
    }
}