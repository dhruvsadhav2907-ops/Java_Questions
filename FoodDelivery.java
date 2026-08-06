// Abstract class used for the anonymous class
abstract class DeliveryStatus {
    public abstract void showStatus();
}

public class FoodDelivery{

    private String customer = "Dhruv";
    private String foodItem = "Pizza";

    public class OrderDetails {
        public void display() {
            System.out.println("Customer: " + customer);
            System.out.println("Food Item: " + foodItem);
        }
    }

    public static void main(String[] args) {

        FoodDelivery app = new FoodDelivery();

        // Instantiating Inner Class
        FoodDelivery.OrderDetails order = app.new OrderDetails();
        order.display();

        // 2. ANONYMOUS CLASS
        DeliveryStatus status = new DeliveryStatus() {
            public void showStatus() {
                System.out.println("Status: Out for delivery!");
            }
        };

        status.showStatus();
    }
}