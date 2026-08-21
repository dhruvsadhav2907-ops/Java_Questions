class Calculator{
    static int count=0;
    int a;
    int b;
    Calculator(){
        System.out.println("Inside Default Constructor");
        a=10;
        b=20;
        count ++;
    }
    Calculator(int x, int y){
        System.out.println("Inside Parameterized constructor");
        a=x;
        b=y;
        count++;
    }
    void display(){
        System.out.println("The values of the variablesare "+ a +" and "+b);
    }
    public static int sum(int a, int b){
        return a+b;
    }
    public static double sum(double a, double b){
        return a + b;
    }
    void countshow(){
        System.out.println("Total number of Objects :"+count);
    }
}
public class Calculator_MethOverLoad{
    public static void main(String[] args){
        Calculator Ca1 = new Calculator();
        Ca1.display();
        Ca1.countshow();
        Calculator Ca2 = new Calculator(4,5);
        System.out.println("The sum of 3 and 4 using sum function :"+Calculator.sum(3,4));
        System.out.println("The sum of 3.14 and 9.80 using the sum fundtion :"+Calculator.sum(3.14,9.92));
        Ca2.countshow();
        Ca2.display();
    }
}