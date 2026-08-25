class Parent{
    void display(){
        System.out.println("Parent Class Method");

    }
}

class Child extends Parent{
    void display(){
        super.display();
        System.out.println("Child class method");
    }
}

public class SuperExample{
    public static void main(String[] args) {
        Child c = new Child();
        c.display();
    }
}