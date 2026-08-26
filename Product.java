import java.util.Scanner;

public class Product{
    public static void main(String[] args) {
        int quantity;
        int price_per_item = 20;
        int total_price;
        int total_quantity=35;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Available Quantity of the selected product : "+total_quantity);
        System.out.println("Enter the quantity that you want to purchase : ");
        try {
            quantity = sc.nextInt();

            if(quantity<=0){
                throw new ArithmeticException("Quantity Must be greater than 0");
            }
            if(quantity>total_quantity){
                throw new ArithmeticException("Our store does not have that much available quantity of the selected product");
            }
            total_quantity-=quantity;
            total_price=price_per_item*quantity;
            System.out.println("Purchase Successful!");
            System.out.println("Your total is : ₹"+total_price);
        } catch (ArithmeticException e) {
            System.out.println("An error has occured :"+e.getMessage());
        } catch (Exception e){
            System.out.println("Invalid input, please enter a valid input");
        } finally{
            sc.close();
            System.out.println("Thank you for shopping with us.");
            System.out.println("Hope to see you again in the future");
        }
    } 
}