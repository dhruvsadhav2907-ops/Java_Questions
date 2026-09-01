import java.util.Scanner;

// Custom Checked Exception for Authentication Failure
class InvalidPasswordException extends java.lang.Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}

public class LoginSystemException {
    // Preset credentials for verification
    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "Password@123";

    // Method to validate login credentials
    public static void authenticate(String username, String password) throws InvalidPasswordException {
        if (!CORRECT_USERNAME.equals(username)) {
            System.out.println("User not found!");
            return;
        }

        // Check password validity
        if (!CORRECT_PASSWORD.equals(password)) {
            throw new InvalidPasswordException("Invalid Password! Access Denied.");
        }

        System.out.println("Login Successful! Welcome, " + username + ".");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            // Attempt authentication
            authenticate(username, password);

        } catch (InvalidPasswordException e) {
            System.out.println("\n[Error Caught]: " + e.getMessage());
        } finally {
            // Cleanup block: always executes regardless of success or failure
            System.out.println("\n[Finally Block]: Closing session and scanner resources.");
            scanner.close();
        }
    }
}