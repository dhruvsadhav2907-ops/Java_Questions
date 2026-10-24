public class SimpleBankRunnable {
    static int balance = 1000;

    public static synchronized void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + " | Balance: " + balance);
    }

    public static synchronized void withdraw(int amount) {
        balance -= amount;
        System.out.println("Withdrawn: " + amount + " | Balance: " + balance);
    }

    public static void main(String[] args) {
        Thread depositThread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                deposit(500);
            }
        });

        Thread withdrawThread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                withdraw(200);
            }
        });

        depositThread.start();
        withdrawThread.start();
    }
}