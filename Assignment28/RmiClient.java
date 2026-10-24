import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClient {
    public static void main(String[] args) {
        String host = (args.length > 0) ? args[0] : "localhost";
        int port = 1099;

        try {
            // Locate registry at the target host/port
            Registry registry = LocateRegistry.getRegistry(host, port);

            // Fetch the stub from the registry
            CalculatorService calculator = (CalculatorService) registry.lookup(RmiServer.BINDING_NAME);
            System.out.println("[Client] Connected to remote service.");

            // Remote invocations
            int num1 = 30;
            int num2 = 6;

            System.out.println("Invoking remote methods with numbers: " + num1 + " and " + num2);
            System.out.println("Result (Addition): " + calculator.add(num1, num2));
            System.out.println("Result (Subtraction): " + calculator.subtract(num1, num2));
            System.out.println("Result (Multiplication): " + calculator.multiply(num1, num2));
            System.out.println("Result (Division): " + calculator.divide(num1, num2));

        } catch (Exception e) {
            System.err.println("[Client] Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}