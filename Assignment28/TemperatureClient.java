import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class TemperatureClient {
    public static void main(String[] args) {
        String host = (args.length > 0) ? args[0] : "localhost";
        int port = 1099;

        try (Scanner scanner = new Scanner(System.in)) {
            // Locate registry and retrieve remote stub
            Registry registry = LocateRegistry.getRegistry(host, port);
            TemperatureConverter converter = (TemperatureConverter) registry.lookup(TemperatureServer.REGISTRATION_NAME);

            System.out.println("=== Connected to Remote Temperature Converter Service ===");

            while (true) {
                System.out.println("\nSelect conversion type:");
                System.out.println("1. Celsius to Fahrenheit");
                System.out.println("2. Fahrenheit to Celsius");
                System.out.println("3. Exit");
                System.out.print("Enter choice (1-3): ");

                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    continue;
                }

                if (choice == 3) {
                    System.out.println("Closing client.");
                    break;
                }

                if (choice != 1 && choice != 2) {
                    System.out.println("Invalid choice. Try again.");
                    continue;
                }

                System.out.print("Enter temperature value: ");
                double tempInput;
                try {
                    tempInput = Double.parseDouble(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid numerical value.");
                    continue;
                }

                // Invoke remote methods
                if (choice == 1) {
                    double result = converter.celsiusToFahrenheit(tempInput);
                    System.out.printf("Result: %.2f °C = %.2f °F%n", tempInput, result);
                } else {
                    double result = converter.fahrenheitToCelsius(tempInput);
                    System.out.printf("Result: %.2f °F = %.2f °C%n", tempInput, result);
                }
            }

        } catch (Exception e) {
            System.err.println("[Client] Communication error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}