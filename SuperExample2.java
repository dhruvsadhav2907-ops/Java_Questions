class Parent{
    Parent(){
        System.out.println("Parent Class Constructor");
    }
}
class Child extends Parent{
    Child(){
        super();
        System.out.println("Child class Constructor");
    }
}

public class SuperExample2{
    public static void main(String[] args) {
        new Child();
    }
}