import java.util.Scanner;

// Custom Checked Exception for ATM PIN Validation
class InvalidPinException extends java.lang.Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}

public class ATMVerificationException {
    // Correct pre-set 4-digit PIN
    private static final int CORRECT_PIN = 4589;

    // Method to verify the entered PIN
    public static void verifyPin(int enteredPin) throws InvalidPinException {
        if (enteredPin != CORRECT_PIN) {
            throw new InvalidPinException("Incorrect PIN entered. Transaction declined.");
        }
        System.out.println("PIN Verified Successfully! Please proceed with your transaction.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter your 4-digit ATM PIN: ");
            int pin = scanner.nextInt();

            // Perform verification
            verifyPin(pin);

        } catch (InvalidPinException e) {
            System.out.println("[Error]: " + e.getMessage());
        } catch (java.lang.Exception e) {
            System.out.println("[Error]: Invalid input format. Please enter numeric digits only.");
        } finally {
            // Guarantees execution whether the PIN is valid or an exception is thrown
            System.out.println("\n[System Notice]: PIN verification process has completed.");
            scanner.close();
        }
    }
}