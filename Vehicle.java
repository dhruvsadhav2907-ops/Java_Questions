// Abstract class to define the vehicle action
abstract class VehicleAction {
    public abstract void performAction();
}

public class Vehicle {

    private String brand;
    private String model;
    private double price;

    // Constructor
    public Vehicle(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    // 1. MEMBER INNER CLASS (Displays Vehicle Details)
    public class VehicleDetails {
        public void display() {
            
            System.out.println("Brand : " + brand);
            System.out.println("Model : " + model);
            System.out.println("Price in Rupees: " + String.format("%.2f", price));
        }
    }

    public static void main(String[] args) {

        // Creating Outer Class Instance
        Vehicle myCar = new Vehicle("Tesla", "Model 3", 4500000.00);

        // Instantiating Member Inner Class to display details
        Vehicle.VehicleDetails details = myCar.new VehicleDetails();
        details.display();

        // 2. ANONYMOUS INNER CLASS (Extends abstract class VehicleAction)
        VehicleAction startEngineAction = new VehicleAction() {
            @Override
            public void performAction() {
                System.out.println("Vehicle started");
            }
        };

        // Performing the action
        startEngineAction.performAction();
     
    }
}