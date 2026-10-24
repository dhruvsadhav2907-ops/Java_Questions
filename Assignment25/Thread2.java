class NumberThread extends Thread {
    private String threadName;
    public NumberThread(String threadName) {
        this.threadName = threadName;
    }
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(threadName + " prints: " + i);
            try {
                // Short sleep to allow the scheduler to interleave thread execution
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(threadName + " was interrupted.");
            }
        }
        System.out.println(threadName + " has finished printing.");
    }
}

public class Thread2 {
    public static void main(String[] args) {
        NumberThread t1 = new NumberThread("Thread-1");
        NumberThread t2 = new NumberThread("Thread-2");
        NumberThread t3 = new NumberThread("Thread-3");

        System.out.println("Starting threads simultaneously...\n");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("\nAll threads finished execution.");
    }
}