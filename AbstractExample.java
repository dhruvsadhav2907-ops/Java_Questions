abstract class Shape{
    abstract void draw();
    void message(){
        System.out.println("This is a shape");
    }
}
class Circle extends Shape{
    void draw(){
        System.out.println("Drawing a circle");
    }
}

public class AbstractExample{
    public static void main(String[] args) {
        Shape s =new Circle();
        Circle c = new Circle();
        c.draw();
        s.draw();
        s.message();
    }
}