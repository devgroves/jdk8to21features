package Threads;

public class PlatformThread {
    public static void main(String[] args) {
        System.out.println("Platform Thread example demo..");
        Thread startThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Normal thread flow...");
            }
        });
        startThread.run();
        Thread.ofPlatform().start(new Runnable() {
            @Override
            public void run() {
                System.out.println("platform thread started..");
            }
        });
    }
}
