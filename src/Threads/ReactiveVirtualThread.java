package Threads;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ReactiveVirtualThread {
    public static void main(String[] args) {
//        ThreadFactory factory = Thread.ofPlatform().name("platformthread-", 0L).factory();
        ThreadFactory factory = Executors.defaultThreadFactory();
        ExecutorService executor = Executors.newFixedThreadPool(5, factory);
        List<String> letters = Arrays.asList("A", "B", "C");
        Scheduler scheduler = Schedulers.from(executor);
        Observable<Long> tick = Observable
                .interval(1, TimeUnit.SECONDS, scheduler);
        Observable.from(letters)
                .zipWith(tick, (string, index) -> {
                    System.out.println("zip with called...");
                    return STR."\{index}-\{string}";
                })
                .subscribeOn(scheduler)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println(Thread.currentThread().getName() + " on completed " + Instant.now());
                        System.out.println(Thread.activeCount());
                        executor.shutdownNow();
                        System.out.println(Thread.activeCount());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println(Instant.now() + " on throw");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(Thread.currentThread().getName() + " " + Instant.now() + "-  on next" + s);
                    }
                });
    }
}
