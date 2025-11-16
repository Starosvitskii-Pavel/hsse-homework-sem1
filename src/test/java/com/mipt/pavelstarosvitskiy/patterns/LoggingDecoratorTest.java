package com.mipt.pavelstarosvitskiy.patterns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class LoggingDecoratorTest {
    DataService simpleDataService;
    DataService loggingDataService;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        simpleDataService = new SimpleDataService();
        loggingDataService = new LoggingDecorator(simpleDataService);

        System.setOut(new PrintStream(outContent));
    }


    @Test
    void shouldLogFindingDataByKey() {
        loggingDataService.saveData("key", "data");

        loggingDataService.findDataByKey("key");

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("Value found: data by key: key"));
    }

    @Test
    void shouldLogSavingData() {
        loggingDataService.saveData("key", "data");

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("Data: data was written with key: key successfully."));
    }

    @Test
    void shouldLogDeletingData() {
        loggingDataService.saveData("key", "data");

        loggingDataService.deleteData("key");

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("Data with key: key was successfully deleted."));
    }
}