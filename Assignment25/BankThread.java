// Shared bank account resource
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Synchronized deposit operation
    public synchronized void deposit(double amount) {
        System.out.println("[Deposit Thread] Initiating deposit of: ₹" + amount);
        double previousBalance = balance;
        balance += amount;
        System.out.println("[Deposit Thread] Deposit successful | Previous: ₹" 
                + previousBalance + " | Updated Balance: ₹" + balance);
        System.out.println("--------------------------------------------------");
    }

    // Synchronized withdrawal operation
    public synchronized void withdraw(double amount) {
        System.out.println("[Withdrawal Thread] Initiating withdrawal of: ₹" + amount);
        if (balance >= amount) {
            double previousBalance = balance;
            balance -= amount;
            System.out.println("[Withdrawal Thread] Withdrawal successful | Previous: ₹" 
                    + previousBalance + " | Updated Balance: ₹" + balance);
        } else {
            System.out.println("[Withdrawal Thread] FAILED: Insufficient funds | Requested: ₹" 
                    + amount + " | Current Balance: ₹" + balance);
        }
        System.out.println("--------------------------------------------------");
    }

    public synchronized double getBalance() {
        return balance;
    }
}

// Thread responsible for deposits
class DepositThread extends Thread {
    private BankAccount account;
    private double[] depositAmounts;

    public DepositThread(BankAccount account, double[] amounts) {
        this.account = account;
        this.depositAmounts = amounts;
    }

    @Override
    public void run() {
        for (double amount : depositAmounts) {
            account.deposit(amount);
            try {
                // Sleep briefly to simulate network/system delay and allow interleaving
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println("Deposit Thread interrupted.");
            }
        }
    }
}

// Thread responsible for withdrawals
class WithdrawalThread extends Thread {
    private BankAccount account;
    private double[] withdrawalAmounts;

    public WithdrawalThread(BankAccount account, double[] amounts) {
        this.account = account;
        this.withdrawalAmounts = amounts;
    }

    @Override
    public void run() {
        for (double amount : withdrawalAmounts) {
            account.withdraw(amount);
            try {
                // Sleep briefly to simulate network/system delay and allow interleaving
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Withdrawal Thread interrupted.");
            }
        }
    }
}

public class BankThread {
    public static void main(String[] args) {
        // Initial balance
        BankAccount sharedAccount = new BankAccount(2000.0);
        System.out.println("=== Bank Transaction Simulation Started ===");
        System.out.println("Initial Account Balance: ₹" + sharedAccount.getBalance());
        System.out.println("==================================================\n");

        // Transaction queues
        double[] deposits = {500.0, 1200.0, 800.0, 1500.0};
        double[] withdrawals = {700.0, 2500.0, 600.0, 400.0};

        // Initialize threads
        DepositThread depositThread = new DepositThread(sharedAccount, deposits);
        WithdrawalThread withdrawThread = new WithdrawalThread(sharedAccount, withdrawals);

        // Start both threads to execute concurrently
        depositThread.start();
        withdrawThread.start();

        try {
            // Wait for both threads to complete execution
            depositThread.join();
            withdrawThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("=== All Transactions Completed ===");
        System.out.println("Final Account Balance: ₹" + sharedAccount.getBalance());
    }
}