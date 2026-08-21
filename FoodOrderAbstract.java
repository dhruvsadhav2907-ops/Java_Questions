abstract class FoodOrder {
    int OrderID;
    String customerName;
    double baseAmount;

    FoodOrder(int OrderID, String customerName, double baseAmount) {
        this.OrderID = OrderID;
        this.customerName = customerName;
        this.baseAmount = baseAmount;
    }

    public abstract double calculateBill();

    public void displayOrderInfo() {
        System.out.println("Order ID : " + OrderID);
        System.out.println("Customer Name : " + customerName);
        System.out.println("Base Amount : " + baseAmount);
    }
}

class DineInOrder extends FoodOrder {
    int tableNumber;
    final double SERVICE_RATE = 0.05;
    final double GST_RATE = 0.05;

    public DineInOrder(int OrderID, String customerName, double baseAmount, int tableNumber) {
        super(OrderID, customerName, baseAmount);
        this.tableNumber = tableNumber;
    }

    public double calculateBill() {
        double service_charge = baseAmount * SERVICE_RATE;
        double gst_charge = baseAmount * GST_RATE;
        return baseAmount + gst_charge + service_charge;
    }

    public void displayBill() {
        displayOrderInfo();

        double service_charge = baseAmount * SERVICE_RATE;
        double gst_charge = baseAmount * GST_RATE;

        System.out.println("Table Number : " + tableNumber);
        System.out.println("Service Charge (5%): " + service_charge);
        System.out.println("GST (5%)           : " + gst_charge);
        System.out.println("Total Bill         : " + calculateBill());
    }
}

class TakeAwayOrder extends FoodOrder {
    private double packagingFee;
    private static final double GST_RATE = 0.05;

    public TakeAwayOrder(int orderId, String customerName,double baseAmount, double packagingFee) {
        super(orderId, customerName, baseAmount);
        this.packagingFee = packagingFee;
    }

    public double calculateBill() {
        double gst = baseAmount * GST_RATE;

        return baseAmount + packagingFee + gst;
    }

    public void displayBill() {
        displayOrderInfo();

        double gst = baseAmount * GST_RATE;

        System.out.println("Packaging Fee : " + packagingFee);
        System.out.println("GST (5%)      : " + gst);
        System.out.println("Total Bill    : " + calculateBill());
    }
}

public class FoodOrderAbstract {
    public static void main(String[] args) {
        DineInOrder dineIn = new DineInOrder(101, "Dhruv Sadhav", 1200.00, 4);
        dineIn.displayBill();
        System.out.println();
        TakeAwayOrder takeAway = new TakeAwayOrder(102, "Aarav Sharma", 650.00, 40.00);
        takeAway.displayBill();
    }
}