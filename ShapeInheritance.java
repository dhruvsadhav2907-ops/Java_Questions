// Parent class
class Shape {
    void calculateArea() {
        System.out.println("Calculating area for shape...");
    }
}

// Subclass 1: Circle
class Circle extends Shape {
    double radius = 7.0;

    void calculateArea() {
        double area = 3.14 * radius * radius;
        System.out.println("Circle Area: " + area);
    }
}

// Subclass 2: Rectangle
class Rectangle extends Shape {
    double length = 10.0;
    double width = 5.0;

    void calculateArea() {
        double area = length * width;
        System.out.println("Rectangle Area: " + area);
    }
}

// Main class
public class ShapeInheritance{
    public static void main(String[] args) {
        
        Circle c = new Circle();
        c.calculateArea();

        Rectangle r = new Rectangle();
        r.calculateArea();
    }
}