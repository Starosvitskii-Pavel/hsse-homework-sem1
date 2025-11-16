package com.mipt.pavelstarosvitskiy.patterns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MetricableDecoratorTest {
    DataService simpleDataService;
    DataService metricableDataService;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        simpleDataService = new SimpleDataService();
        metricableDataService = new MetricableDecorator(simpleDataService);

        System.setOut(new PrintStream(outContent));
    }

    @Test
    void shouldMeasureFindingDataByKeyTime() {
        metricableDataService.saveData("key", "data");
        metricableDataService.findDataByKey("key");

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("Метод выполнялся: PT"));
        assertTrue(consoleOutput.contains("S"));
    }

    @Test
    void shouldMeasureSavingDataTime() {
        metricableDataService.saveData("key", "data");

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("Метод выполнялся: PT"));
        assertTrue(consoleOutput.contains("S"));
    }

    @Test
    void shouldMeasureDeletingDataTime() {
        metricableDataService.saveData("key", "data");
        metricableDataService.deleteData("key");

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("Метод выполнялся: PT"));
        assertTrue(consoleOutput.contains("S"));
    }
}