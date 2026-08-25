final class FinalClass{
    void display(){
        System.out.println("This is a final class");
    }
}
public class FinalClassExample{
    public static void main(String[] args) {
        FinalClass obj = new FinalClass();
        obj.display(); 
    }
}