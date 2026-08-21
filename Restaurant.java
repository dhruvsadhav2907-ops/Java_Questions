public class Restaurant{
    private static int totalOrders = 0;        
    private static double totalRevenue = 0.0;  
    private static final double GST_RATE = 0.05; 

    private int orderId;
    private String customerName;
    private double baseAmount;
    private double finalBillAmount;

    public Restaurant() {
        totalOrders++;
        this.orderId = totalOrders;
        this.customerName = "Guest";
        this.baseAmount = 0.0;
        this.finalBillAmount = 0.0;
    }

    public Restaurant(String customerName, double baseAmount) {
        totalOrders++;
        this.orderId = totalOrders;
        this.customerName = customerName;
        this.baseAmount = baseAmount;
        this.finalBillAmount = 0.0;
    }

    public double calculateBill(double serviceCharge) {
        double tax = this.baseAmount * GST_RATE;
        this.finalBillAmount = this.baseAmount + tax + serviceCharge;
        totalRevenue += this.finalBillAmount;
        return this.finalBillAmount;
    }

    public double calculateBill(double packagingCharge, boolean isTakeaway) {
        double tax = this.baseAmount * GST_RATE;
        this.finalBillAmount = this.baseAmount + tax + packagingCharge;
        totalRevenue += this.finalBillAmount;
        return this.finalBillAmount;
    }

    public double calculateBill(double distanceInKm, double deliveryChargePerKm) {
        double tax = this.baseAmount * GST_RATE;
        double totalDeliveryFee = distanceInKm * deliveryChargePerKm;
        this.finalBillAmount = this.baseAmount + tax + totalDeliveryFee;
        totalRevenue += this.finalBillAmount;
        return this.finalBillAmount;
    }
    public void displayBillDetails(String orderType) {
        System.out.println("Order ID      : " + this.orderId);
        System.out.println("Customer Name : " + this.customerName);
        System.out.println("Order Type    : " + orderType);
        System.out.println("Base Amount   : ₹" + this.baseAmount);
        System.out.println("Final Bill    : ₹" + String.format("%.2f", this.finalBillAmount));
      
    }

    // Static Method to show summary across all orders
    public static void displayRestaurantSummary() {
       
        System.out.println("Total Orders Processed : " + totalOrders);
        System.out.println("Total Revenue Generated : ₹" + String.format("%.2f", totalRevenue));
        
    }

    // --- 6. MAIN METHOD ---
    public static void main(String[] args) {

        // Order 1: Dine-In (Uses Parameterized Constructor & Dine-In calculateBill)
        Restaurant order1 = new Restaurant("Aarav Sharma", 500.0);
        order1.calculateBill(50.0); // ₹50 service charge
        order1.displayBillDetails("Dine-In");

        // Order 2: Takeaway (Uses Parameterized Constructor & Takeaway calculateBill)
        Restaurant order2 = new Restaurant("Ananya Patel", 350.0);
        order2.calculateBill(20.0, true); // ₹20 packaging charge
        order2.displayBillDetails("Takeaway");

        // Order 3: Delivery (Uses Default Constructor + Manual Setup & Delivery calculateBill)
        Restaurant order3 = new Restaurant();
        order3.customerName = "Rohan Mehta";
        order3.baseAmount = 800.0;
        order3.calculateBill(5.5, 12.0); // 5.5 km distance @ ₹12/km
        order3.displayBillDetails("Delivery");

        // Calling Static Method using Class Name (Avoids static access warnings!)
        Restaurant.displayRestaurantSummary();
    }
}