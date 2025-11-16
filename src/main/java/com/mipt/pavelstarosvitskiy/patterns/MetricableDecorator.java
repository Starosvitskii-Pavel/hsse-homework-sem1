package com.mipt.pavelstarosvitskiy.patterns;

import java.time.Duration;
import java.util.Optional;

public class MetricableDecorator implements DataService {
    private final DataService original;
    MetricService metricService = new MetricService();

    public MetricableDecorator(DataService original) {
        this.original = original;
    }

    public static class MetricService {
        public void sendMetric(Duration duration) {
            System.out.println("Метод выполнялся: " + duration.toString());
        }
    }

    @Override
    public Optional<String> findDataByKey(String key) {
        long startNanoTime = System.nanoTime();
        Optional<String> data = original.findDataByKey(key);
        long endNanoTime = System.nanoTime();

        metricService.sendMetric(Duration.ofNanos(endNanoTime-startNanoTime));
        return data;
    }

    @Override
    public void saveData(String key, String data) {
        long startNanoTime = System.nanoTime();
        original.saveData(key, data);
        long endNanoTime = System.nanoTime();

        metricService.sendMetric(Duration.ofNanos(endNanoTime-startNanoTime));
    }

    @Override
    public boolean deleteData(String key) {
        long startNanoTime = System.nanoTime();
        boolean isDeleted = original.deleteData(key);
        long endNanoTime = System.nanoTime();

        metricService.sendMetric(Duration.ofNanos(endNanoTime-startNanoTime));
        return isDeleted;
    }
}
