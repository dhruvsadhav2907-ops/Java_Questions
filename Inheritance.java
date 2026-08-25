/*class Animal{
    void eat(){
        System.out.println("this animal eats food");
    }
}
class Dog extends Animal{
    void bark(){
        System.out.println("Dog barks!");
    }
}
class Cat extends Animal{
    void drink(){
        System.out.println("The cat drinks milk");
    }
}
class Cow extends Animal{
    void product(){
        System.out.println("Cow gives milk which can be used to make ice cream!!");
    }
}
public class Inheritance{
    public static void main(String[] args) {
        Dog d =new Dog();
        d.eat();
        d.bark();
        Cat c = new Cat();
        Cow co = new Cow();
        c.drink();
        co.product();
    }
}
class Grandparent{
    void showgrandparent(){
        System.out.println("Grandparent class method");
    }
}
class Parent extends Grandparent{
    void showParent(){
        System.out.println("Parent class method");
    }
}
class Child extends Parent{
    void showChild(){
        System.out.println("Child class method");
    }
}
public class Inheritance{
    public static void main(String[] args) {
        Child c =new Child();
        c.showgrandparent();
        c.showParent();
        c.showChild();
    }
}
interface Animal{
    void sound();

}
class Dog implements Animal{
    public void sound(){
        System.out.println("Dog Barks");
    }
}
public class Inheritance{
    public static void main(String[] args){
        Animal a = new Dog();
        a.sound();
    }
}*/
interface A{
    void showA();
}
interface B{
    void showB();
}
class C implements A,B{
    public void showA(){
        System.out.println("Implemented ShowA in class C!!");
    }
    public void showB(){
        System.out.println("Implemented ShowB in class C!!");
    }
}
public class Inheritance {
    public static void main(String[] args) {
        C c = new C();
        c.showA();
        c.showB();
    }
}