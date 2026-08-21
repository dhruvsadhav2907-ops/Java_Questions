class Animal{

    public void sound(){
        System.out.println("The animal Makes a sound!");
    }

}

class Dog extends Animal{
    public void sound(){
        super.sound();
        System.out.println("The dog is barking!");
    }
}
class Cat extends Animal{
    public void sound(){
        super.sound();
        System.out.println("The cat is Meowing!");
    }
}

public class SuperKeyword{
    public static void main(String[] args){
        Dog d = new Dog();
        d.sound();
        Cat c = new Cat();
        c.sound();
    }
}