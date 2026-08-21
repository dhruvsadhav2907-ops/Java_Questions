// 1. HIERARCHICAL INHERITANCE
class Animal {
    void eat() {
        System.out.println("This animal eats food.");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog barks!");
    }
}

class Cat extends Animal {
    void drink() {
        System.out.println("The cat drinks milk.");
    }
}

class Cow extends Animal {
    void product() {
        System.out.println("Cow gives milk which can be used to make ice cream!!");
    }
}



class Grandparent {
    void showGrandparent() {
        System.out.println("Grandparent class method");
    }
}

class Parent extends Grandparent {
    void showParent() {
        System.out.println("Parent class method");
    }
}

class Child extends Parent {
    void showChild() {
        System.out.println("Child class method");
    }
}


interface Soundable {
    void sound();
}

class PetDog implements Soundable {
    public void sound(){
        System.out.println("Dog Barks");
    }
}



interface InterfaceA {
    void showA();
}

interface InterfaceB {
    void showB();
}

class CombinedClass implements InterfaceA, InterfaceB {
    public void showA() {
        System.out.println("Implemented ShowA in class!");
    }

    public void showB() {
        System.out.println("Implemented ShowB in class!");
    }
}

public class Inheritance2 {
    public static void main(String[] args) {

       
        Dog d = new Dog();
        d.eat();
        d.bark();

        Cat c = new Cat();
        c.drink();

        Cow co = new Cow();
        co.product();


        
        Child childObj = new Child();
        childObj.showGrandparent();
        childObj.showParent();
        childObj.showChild();


        
        Soundable pet = new PetDog();
        pet.sound();


        
        CombinedClass combinedObj = new CombinedClass();
        combinedObj.showA();
        combinedObj.showB();
    }
}