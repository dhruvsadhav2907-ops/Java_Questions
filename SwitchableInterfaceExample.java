interface Switchable{
    void turnOn();
}

class Light implements Switchable{
    public void turnOn(){
        System.out.println("The light has turned on.");
    }
}

class Fan implements Switchable{
    public void turnOn(){
        System.out.println("The fan is being Switched on.");
    }
}

public class SwitchableInterfaceExample{
    public static void main(String[] args) {
        Light l1 = new Light();
        Fan f1 = new Fan();
        l1.turnOn();
        f1.turnOn();
    }
}
