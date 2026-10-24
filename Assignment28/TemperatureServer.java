import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TemperatureServer {
    public static final String REGISTRATION_NAME = "TempConverterService";
    public static final int REGISTRY_PORT = 1099;

    public static void main(String[] args) {
        try {
            // Start RMI registry on port 1099
            Registry registry = LocateRegistry.createRegistry(REGISTRY_PORT);
            System.out.println("[Server] RMI Registry initiated on port " + REGISTRY_PORT);

            // Instantiate remote service
            TemperatureConverter converter = new TempConImpl();

            // Bind the remote object stub in the registry
            registry.rebind(REGISTRATION_NAME, converter);
            System.out.println("[Server] Service '" + REGISTRATION_NAME + "' is ready for incoming requests.");

        } catch (Exception e) {
            System.err.println("[Server] Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}