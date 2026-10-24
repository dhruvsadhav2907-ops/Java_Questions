import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorServiceImpl extends UnicastRemoteObject implements CalculatorService {

    public CalculatorServiceImpl() throws RemoteException {
        super(); // Exports the remote object on an anonymous ephemeral port
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        System.out.println("[Server] Executing add(" + a + ", " + b + ")");
        return a + b;
    }

    @Override
    public int subtract(int a, int b) throws RemoteException {
        System.out.println("[Server] Executing subtract(" + a + ", " + b + ")");
        return a - b;
    }

    @Override
    public int multiply(int a, int b) throws RemoteException {
        System.out.println("[Server] Executing multiply(" + a + ", " + b + ")");
        return a * b;
    }

    @Override
    public double divide(int a, int b) throws RemoteException {
        System.out.println("[Server] Executing divide(" + a + ", " + b + ")");
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return (double) a / b;
    }
}