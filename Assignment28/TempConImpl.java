import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TempConImpl extends UnicastRemoteObject implements TemperatureConverter {

    public TempConImpl() throws RemoteException {
        super();
    }

    @Override
    public double celsiusToFahrenheit(double celsius) throws RemoteException {
        System.out.printf("[Server] Converting %.2f °C to Fahrenheit%n", celsius);
        return (celsius * 9.0 / 5.0) + 32.0;
    }

    @Override
    public double fahrenheitToCelsius(double fahrenheit) throws RemoteException {
        System.out.printf("[Server] Converting %.2f °F to Celsius%n", fahrenheit);
        return (fahrenheit - 32.0) * 5.0 / 9.0;
    }
}