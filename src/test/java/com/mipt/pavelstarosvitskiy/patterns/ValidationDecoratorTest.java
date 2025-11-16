package com.mipt.pavelstarosvitskiy.patterns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ValidationDecoratorTest {
    DataService simpleDataService;
    DataService validationDataService;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        simpleDataService = new SimpleDataService();
        validationDataService = new ValidationDecorator(simpleDataService);

        System.setOut(new PrintStream(outContent));
    }

    @Test
    void shouldValidateFindingDataByKey() {
        validationDataService.saveData("key", "data");
        validationDataService.findDataByKey("key");

        String consoleOutput = outContent.toString();

        assertFalse(consoleOutput.contains("Data should not be null!"));
        assertFalse(consoleOutput.contains("String should not be empty!"));
    }

    @Test
    void shouldNotValidateFindingDataByKey() {
        validationDataService.saveData("key", "");
        validationDataService.findDataByKey("key");

        String consoleOutput = outContent.toString();

        assertFalse(consoleOutput.contains("Data should not be null!"));
        assertTrue(consoleOutput.contains("String should not be empty!"));
    }

    @Test
    void shouldValidateSavingData() {
        validationDataService.saveData("key", "data");

        String consoleOutput = outContent.toString();

        assertFalse(consoleOutput.contains("Data should not be null!"));
        assertFalse(consoleOutput.contains("String should not be empty!"));
    }

    @Test
    void shouldNotValidateSavingData() {
        validationDataService.saveData("key", "");

        String consoleOutput = outContent.toString();

        assertFalse(consoleOutput.contains("Data should not be null!"));
        assertTrue(consoleOutput.contains("String should not be empty!"));
    }


    @Test
    void shouldValidateDeletingData() {
        validationDataService.saveData("key", "data");
        validationDataService.deleteData("key");

        String consoleOutput = outContent.toString();

        assertFalse(consoleOutput.contains("Data should not be null!"));
    }
}