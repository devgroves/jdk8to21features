package Threads;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualThreadDemo {
    static final AtomicInteger atomicInteger = new AtomicInteger();
    static Runnable runnable = () -> {
        try {
            Thread.sleep(Duration.ofSeconds(1));
        } catch(Exception e) {
            System.out.println(e);
        }
//        System.out.print("Work Done - " + atomicInteger.incrementAndGet());
    };

    public static void main(String args[]) {
        virtualThreadDemo();
        osThreadDemo();
    }

    private static void osThreadDemo() {
        Instant start = Instant.now();
        try (var executor = Executors.newFixedThreadPool(100)) {
            for(int i = 0; i < 10_000; i++) {
                executor.submit(runnable);
            }
        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toSeconds();
        System.out.println("OS Thread Demo: Total elapsed time in seconds: " + timeElapsed);
    }

    public static void virtualThreadDemo() {
        Instant start = Instant.now();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for(int i = 0; i < 10_000; i++) {
                executor.submit(runnable);
            }
        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toSeconds();
        System.out.println("Virtual Thread Demo: Total elapsed time in seconds: " + timeElapsed);
    }
}
