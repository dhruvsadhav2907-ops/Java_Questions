
interface Taxable {
    double calculateTax();
}


class Product {
    String id;
    String name;
    double price;

    Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
    }
}
class Electronics extends Product implements Taxable {
    
    Electronics(String id, String name, double price) {
        super(id, name, price);
    }
    public double calculateTax() {
        return price * 0.10; 
    }

    void display() {
        super.display();
        System.out.println("Tax: " + calculateTax());
        System.out.println("Total: " + (price + calculateTax()));
        
    }
}
class Clothing extends Product implements Taxable {

    Clothing(String id, String name, double price) {
        super(id, name, price);
    }
    public double calculateTax() {
        return price * 0.05; 
    }

    void display() {
        super.display();
        System.out.println("Tax: " + calculateTax());
        System.out.println("Total: " + (price + calculateTax()));
    }
}
class Grocery extends Product {

    Grocery(String id, String name, double price) {
        super(id, name, price);
    }

    void display() {
        super.display();
        System.out.println("Tax: 0 (Tax Free)");
        System.out.println("Total: " + price);
    }
}

public class Products {
    public static void main(String[] args) {

        Electronics laptop = new Electronics("E1", "Laptop", 50000);
        Clothing shirt = new Clothing("C1", "Shirt", 1000);
        Grocery milk = new Grocery("G1", "Milk", 50);

        System.out.println("E-COMMERCE PRODUCTS ");
        laptop.display();
        shirt.display();
        milk.display();
    }
}