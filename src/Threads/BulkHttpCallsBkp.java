package Threads;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class BulkHttpCallsBkp {
    public static void main(String[] args) throws IOException {
        File zipcodesListFile = new File("us-cities-demographics.csv");
        Scanner scanner = new Scanner(zipcodesListFile);
        scanner.useDelimiter("\n");
        boolean isHeader = true;

        Collection<ZipApiThread> zipApiThreadList = new ArrayList<>();
        while (scanner.hasNext()) {
            String readLine = scanner.next();
            if (isHeader) {
                isHeader = false;
                continue;
            }
            String recordParts[] = readLine.split(";");
            String city = recordParts[0].replace(" ", "%20");
            String stateAbbr = recordParts[9];

            System.out.println("zipcode ..." + city + " " + stateAbbr);
            ZipApiThread zipApiThread = new ZipApiThread(stateAbbr, city);
            zipApiThreadList.add(zipApiThread);
        }
        System.out.println("Enter 1 for virtual and 2 for platform:");
        Scanner in = new Scanner(System.in);
        String choice = in.next();
        Instant startTime = Instant.now();
        ThreadFactory factory;

        if (choice.equalsIgnoreCase("1")) {
            factory = Thread.ofVirtual().name("my-name", 0L).factory();
        } else {
            factory = Thread.ofPlatform().name("my-name", 0L).factory();
        }
        try (var executor = Executors.newFixedThreadPool(3000, factory)) {
            Instant midTime = Instant.now();
            System.out.println("Start Time" + startTime + " end time " + midTime);
            System.out.println("Total Time" + ChronoUnit.SECONDS.between(startTime, midTime));
//        ThreadFactory factory = Thread.ofVirtual().name("my-name", 0L).factory();
//        try (var executor = Executors.newThreadPerTaskExecutor(factory)) {
            try {
                List<Future<String>> resultFutures = executor.invokeAll(zipApiThreadList);
//                                executor.awaitTermination(1, TimeUnit.MINUTES);
                System.out.println("active count" + Thread.activeCount());
                int successCnt = 0, cancelCnt = 0, notCompleted = 0;
                for (Future<String> resultElement: resultFutures) {
                    if (resultElement.isDone()) {
                        successCnt++;
                    } else if (resultElement.isCancelled()) {
                        cancelCnt++;
                    } else {
                        notCompleted++;
                    }
                }
                System.out.println(" "+successCnt+ " " + cancelCnt + " " + notCompleted);
                executor.shutdown();
                System.out.println("active count" + Thread.activeCount());
                executor.shutdownNow();
                System.out.println("active count" + Thread.activeCount());

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//                executor.submit(() -> {
//                    BulkHttpCalls bulkHttpCalls = new BulkHttpCalls();
//                    try {
//                        bulkHttpCalls.getZipCodeDetails(city, stateAbbr);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                });

        }
        Instant endTime = Instant.now();
        System.out.println("Start Time" + startTime + " end time " + endTime);
        System.out.println("Total Time" + ChronoUnit.SECONDS.between(startTime, endTime));
    }

    public static class ZipApiThread implements Callable<String> {
        String stateAbbr;
        String city;
        public ZipApiThread(String stateAbbr, String city) {
            this.stateAbbr = stateAbbr;
            this.city = city;
        }

        @Override
        public String call() {
            String zipcodeByCity = STR."https://api.zippopotam.us/us/\{stateAbbr}/\{city}";
            System.out.println(zipcodeByCity);
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(zipcodeByCity);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()))) {
                    for (String line; (line = reader.readLine()) != null; ) {
                        result.append(line);
                    }
                } catch (FileNotFoundException exception) {
                    System.out.println("zip code details not found " + zipcodeByCity);
                }
                System.out.println(STR."**********City: \{city} StateAbbr: \{stateAbbr}******");
                System.out.println(result.toString());
                JSONObject parseJson = new JSONObject(result.toString());
                JSONArray jsonArray = parseJson.getJSONArray("places");
                for (int i=0; i<jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String zipCode = jsonObject.getString("post code");
//                    getZipCodeDetails(zipCode);
                }
            } catch (IOException exception) {
                System.out.println("io exception.. " + zipcodeByCity);
                exception.printStackTrace();
                throw new RuntimeException(exception);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
            return result.toString();
        }

        public void getZipCodeDetails(String zipCode) throws IOException {
            String zipcodeUrl = STR."https://api.zippopotam.us/us/\{zipCode}";
            System.out.println("zipcode url -"+zipcodeUrl);
            StringBuilder result = new StringBuilder();
            URL url = new URL(zipcodeUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    result.append(line);
                }
            } catch(FileNotFoundException exception) {
                System.out.println("zip code details not found "+zipcodeUrl);
            }
            System.out.println(STR."**********\{zipCode}******");
            System.out.println(result.toString());
        }
    }
    public void getZipCodeDetails(String city, String stateAbbr) throws IOException {
        String zipcodeByCity = STR."https://api.zippopotam.us/us/\{stateAbbr}/\{city}";
        System.out.println(zipcodeByCity);
        StringBuilder result = new StringBuilder();
        URL url = new URL(zipcodeByCity);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        } catch(FileNotFoundException exception) {
            System.out.println("zip code details not found "+zipcodeByCity);
        }
        System.out.println(STR."**********City: \{city} StateAbbr: \{stateAbbr}******");
        System.out.println(result.toString());
    }
    public void getZipCodeDetails(String zipCode) throws IOException {
        String zipcodeUrl = STR."https://api.zippopotam.us/us/\{zipCode}";
        System.out.println("zipcode url -"+zipcodeUrl);
        StringBuilder result = new StringBuilder();
        URL url = new URL(zipcodeUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        } catch(FileNotFoundException exception) {
            System.out.println("zip code details not found "+zipcodeUrl);
        }
//        System.out.println(STR."**********\{zipCode}******");
//        System.out.println(result.toString());
    }
}
