class FinalVariable{
    final double pi = 3.14d;
    void display(){
        System.out.println("The value of the final variable is "+pi);
    }
}
class Parent{
    final void displayParent(){
        System.out.println("Final method in parent class");
    }
}

class Child extends Parent{
    //void display()
    void displayChild(){
        System.out.println("This method is in child class.");
    }
}
final class FinalClass{
    void show(){
        System.out.println("This is a final class and it cannot be instantiated ");
    }
}

public class FinalExample{
    public static void main(String[] args) {
        System.out.println("Final Variable :");
        FinalVariable x = new FinalVariable();
        x.display();
        System.out.println(" ");
        System.out.println("Final method(cannot be overridded in subclass)");
        Child c = new Child();
        c.displayParent();
        c.displayChild();
        System.out.println(" ");
        System.out.println("Final Class");
        FinalClass f1 = new FinalClass();
        f1.show();
    }
}