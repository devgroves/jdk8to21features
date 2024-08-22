package Threads;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MultiFileCopyUsingThreads {
    public static void main(String[] args) throws IOException {
        String inFile = "2023-12-15 20-09-06.mkv";
        Collection<FileCopyThread> fileCopyTasks = new ArrayList<>();
        System.out.println("Enter no of copies:");
        Scanner in = new Scanner(System.in);
        Integer outFileCnt = in.nextInt();
        for (int i=0; i< outFileCnt; i++) {
            FileCopyThread fileCopyThread = new FileCopyThread(inFile, "out/outFile-"+i+".mkv");
            fileCopyTasks.add(fileCopyThread);
        }
        System.out.println("Enter 1 for virtual, and 2 for platform, 3 for native thread:");
        String choice = in.next();
        Instant startTime = Instant.now();
        ThreadFactory factory;

        if (choice.equalsIgnoreCase("1")) {
            factory = Thread.ofVirtual().name("virtualthread-", 0L).factory();
        } else if (choice.equalsIgnoreCase("2")) {
            factory = Thread.ofPlatform().name("platformthread-", 0L).factory();
        } else {
            factory = Executors.defaultThreadFactory();
        }
        try (var executor = Executors.newFixedThreadPool(3000, factory)) {
            Instant midTime = Instant.now();
            System.out.println("Start Time :" + startTime + " end time (after reading zip file) :" + midTime);
            System.out.println("Total Time (in milli seconds):" + ChronoUnit.MILLIS.between(startTime, midTime));
            try {
                System.out.println("Processing...");
                executor.invokeAll(fileCopyTasks);
                System.out.println("active count after invoke all-" + Thread.activeCount());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        Instant endTime = Instant.now();
        System.out.println("Start Time :" + startTime + " end time :" + endTime);
        System.out.println("Total Time (in seconds):" + ChronoUnit.SECONDS.between(startTime, endTime));
    }

    static class FileCopyThread  implements Callable<Void> {
        String inFile, outFile;
        public FileCopyThread(String inFile, String outFile) {
            this.inFile = inFile;
            this.outFile = outFile;
        }

        @Override
        public Void call() {
            Path inputPath = FileSystems.getDefault().getPath(inFile);
            try {
                Files.copy(inputPath, new FileOutputStream(outFile));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }
}
