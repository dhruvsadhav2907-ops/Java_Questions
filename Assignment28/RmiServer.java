import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer {
    public static final String BINDING_NAME = "CalculatorService";
    public static final int REGISTRY_PORT = 1099;

    public static void main(String[] args) {
        try {
            // Programmatically start RMI registry on default port 1099
            Registry registry = LocateRegistry.createRegistry(REGISTRY_PORT);
            System.out.println("[Server] RMI Registry started on port " + REGISTRY_PORT);

            // Instantiate remote service
            CalculatorService service = new CalculatorServiceImpl();

            // Bind the remote object stub to the registry
            registry.rebind(BINDING_NAME, service);
            System.out.println("[Server] '" + BINDING_NAME + "' bound successfully. Ready for client calls.");

        } catch (Exception e) {
            System.err.println("[Server] Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}