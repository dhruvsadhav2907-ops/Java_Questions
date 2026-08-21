class ConstructorA{
    int age;
    String name;
    char gender;
    String email;

    ConstructorA() {
        age=18;
        name="Dhruv";
        gender ='M';
        email="Dhruvsadhav@gmail.com";
        System.out.println("Inside Default Constructor");
    }

    ConstructorA(int a, String n, char g, String e) {
        age = a;
        name = n;
        gender = g;
        email = e;
        System.out.println("Inside PArameterized Constructor");
    }
    ConstructorA(ConstructorA c){
        this.age=c.age;
        this.name=c.name;
        this.gender=c.gender;
        this.email=c.email;
        System.out.println("Inside Copy Constructor");
    }
    void display(){
        System.out.println("Name :"+name);
        System.out.println("Age :"+age);
        System.out.println("Gender :"+gender);
        System.out.println("Email :"+email);
    }
}
public class Myclass{
    public static void main(String[] args){
        ConstructorA C1 = new ConstructorA();
        C1.display();
        ConstructorA C2 = new ConstructorA(24,"Rohan",'m',"Rohan@gmail.com");
        C2.display();
        ConstructorA C3 = new ConstructorA(C2);
        C3.display();
    }
}