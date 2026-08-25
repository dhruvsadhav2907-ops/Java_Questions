interface p1{
    void display();
}

interface p2{
    void show();
}

class A implements p1,p2{
    public void display(){
        System.out.println("P1 interface method");
    }

    public void show(){
        System.out.println("P2 interface method");
    }
}

public class MultipleInheritence{
    public static void main(String[] args) {
        A a1 = new A();
        a1.show();
        a1.display();
    }
}