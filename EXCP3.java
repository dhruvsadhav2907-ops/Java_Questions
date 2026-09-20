import java.util.Scanner;

// Step 1: Define the custom checked exception
class UnderAgeException extends java.lang.Exception {
    public UnderAgeException(String message) {
        super(message);
    }
}

public class EXCP3 {

    // Step 2: Validation method declaring 'throws' and using 'throw'
    public static void checkEligibility(int age) throws UnderAgeException {
        if (age < 18) {
            throw new UnderAgeException("Access Denied: You must be at least 18 years old to apply for a driving license.");
        }
        System.out.println("Eligibility Confirmed: You are eligible for a driving license!");
    }

    // Step 3: Handling the custom exception using try-catch-finally
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();

            checkEligibility(age);

        } catch (UnderAgeException e) {
            System.out.println("\n[Application Rejected]: " + e.getMessage());
        } catch (java.lang.Exception e) {
            System.out.println("\n[Input Error]: Please enter a valid numerical age.");
        } finally {
            System.out.println("[System]: Driving license verification process finished.");
            scanner.close();
        }
    }
}