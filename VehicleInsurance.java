class Vehicle {
    protected String vehicleNumber;
    protected String brand;
    protected String model;
    protected double basePrice;

    public Vehicle(String vehicleNumber, String brand, String model, double basePrice) {
        this.vehicleNumber = vehicleNumber;
        this.brand = brand;
        this.model = model;
        this.basePrice = basePrice;
    }
    public void displayVehicleDetails() {
        System.out.println("Vehicle No : " + vehicleNumber);
        System.out.println("Brand      : " + brand);
        System.out.println("Model      : " + model);
        System.out.println("Base Price : ₹" + String.format("%.2f", basePrice));
    }
}

class CarInsurance extends Vehicle {
    private String policyNumber;
    private int seatingCapacity;

    public CarInsurance(String vehicleNumber, String brand, String model, double basePrice, String policyNumber, int seatingCapacity) {
        super(vehicleNumber, brand, model, basePrice); 
        this.policyNumber = policyNumber;
        this.seatingCapacity = seatingCapacity;
    }

    public double calculateCarPremium() {
        return (super.basePrice * 0.05) + (seatingCapacity * 500); 
    }

    public void displayCarPolicy() {
        super.displayVehicleDetails(); 
        System.out.println("Policy No  : " + policyNumber);
        System.out.println("Capacity   : " + seatingCapacity + " Seats");
        System.out.println("Premium Due: ₹" + String.format("%.2f", calculateCarPremium()));
    }
}

class BikeInsurance extends Vehicle {
    private String policyNumber;
    private int engineCC;

    public BikeInsurance(String vehicleNumber, String brand, String model, double basePrice, String policyNumber, int engineCC) {
        super(vehicleNumber, brand, model, basePrice); // 1. super constructor call
        this.policyNumber = policyNumber;
        this.engineCC = engineCC;
    }

    public double calculateBikePremium() {
        double ccCharge = (engineCC > 200) ? 1200.0 : 600.0;
        return (super.basePrice * 0.03) + ccCharge; 
    }

    public void displayBikePolicy() {
        super.displayVehicleDetails(); 
        System.out.println("Policy No  : " + policyNumber);
        System.out.println("Engine CC  : " + engineCC + " cc");
        System.out.println("Premium Due: ₹" + String.format("%.2f", calculateBikePremium()));
    }
}

public class VehicleInsurance{
    public static void main(String[] args) {
        CarInsurance car = new CarInsurance("MH-12-AB-1234", "Hyundai", "Creta", 1400000.0, "POL-CAR-901", 5);
        BikeInsurance bike = new BikeInsurance("MH-14-CD-5678", "Royal Enfield", "Hunter 350", 180000.0, "POL-BIKE-402", 349);
        car.displayCarPolicy();
        bike.displayBikePolicy();
    }
}