package test.java;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import main.java.CountryLocator;

public class PerformanceTesting {
    private static final int REQUESTS_PER_SECOND = 100;
    private static final int TEST_DURATION_SECONDS = 10; 

    public static void main(String[] args) {
        CountryLocator locator = new CountryLocator();
        ExecutorService executor = Executors.newFixedThreadPool(REQUESTS_PER_SECOND);

        long[] executionTimes = new long[REQUESTS_PER_SECOND * TEST_DURATION_SECONDS];

        for (int i = 0; i < REQUESTS_PER_SECOND * TEST_DURATION_SECONDS; i++) {
            int index = i;
            executor.submit(() -> {
            	Random random = new Random();
				double latitude = -90 + (90 - (-90)) * random.nextDouble();
                double longitude = -180 + (180 - (-180)) * random.nextDouble();
                long start = System.nanoTime();
                locator.getCountryCode(latitude, longitude); 
                long end = System.nanoTime();
                executionTimes[index] = end - start;
            });

           
            if ((i + 1) % REQUESTS_PER_SECOND == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(TEST_DURATION_SECONDS + 1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long totalExecutionTime = 0;
        for (long time : executionTimes) {
            totalExecutionTime += time;
        }
        double averageExecutionTime = totalExecutionTime / (double) (REQUESTS_PER_SECOND * TEST_DURATION_SECONDS);
        System.out.println("Average execution time: " + averageExecutionTime / 1_000_000.0 + " ms");
    }
}
