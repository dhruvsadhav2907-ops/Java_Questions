class Outer{
    private String message = "Hello from Outer class";
    //inner class
    class Inner{
        void display(){
            System.out.println("Message :"+ message);
        }
    }
    void show(){
        System.out.println("Inside Show Method");
    }
    
}
class Animal{
    void sound(){
        System.out.println("Animal makes sound");
    }
}
public class InnerAnonymousDemo{
    public static void main(String[] args) {
        Outer obj = new Outer();
        Outer.Inner in = obj.new Inner();
        in.display();

        Animal a =new Animal(){
            void sound(){
                super.sound();
                System.out.println("Dog Barks!!");
            }
        };
        a.sound();
    }
}