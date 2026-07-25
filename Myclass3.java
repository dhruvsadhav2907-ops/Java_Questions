class Mobile{
    String name; 
    String network;
    int price;
    int storage;
    String color;
    String company;

    Mobile(){
        name="S25 Ultra";
        network = "5G";
        price=125000;
        storage=256;
        color="Greyish-black";
        company="Samsung";
    }
    Mobile(String n, String ne, int p, int s, String c, String co){
        name=n;
        network=ne;
        price=p;
        storage=s;
        color=co;
    }
    Mobile(Mobile m){
        this.name=m.name;
        this.network=m.network;
        this.price=m.price;
        this.storage=m.storage;
        this.color=m.color;
        this.company=m.company;
    }
    void display(){
        System.out.println("Name :"+name);
        System.out.println("Network :"+network);
        System.out.println("price :"+price);
        System.out.println("Storage :"+storage);
        System.out.println("color :"+color);
        System.out.println("Company :"+company);
    }
}

public class Myclass3{
    public static void main(String[] args){
        Mobile m1 = new Mobile();
        m1.display();
        Mobile m2 = new Mobile("S26 Ultra","5G",150000,256,"Marine-blue","Samsung");
        m2.display();
        Mobile m3 = new Mobile(m2);
        m3.display();
    }
}