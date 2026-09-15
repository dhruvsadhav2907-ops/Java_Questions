public class Assignment1{
    static void sum(int a, int b){
        int result=a+b;
        System.out.println("The sum is "+result);
    }
    static void difference(int a, int b){
        int result2 = a-b;
        System.out.println("The difference is "+result2);
    }
    static void product(int a,int b){
        int result3 = a*b;
        System.out.println("The product is "+result3);
    }
    static void quotient(int a, int b){
        if(b==0){
            System.out.println("Division by zero not allowed");
            return;
        }
        double result4 = a/b;
        System.out.println("The quotient is "+result4);
    }
    public static void main(String[] args) {
        int num1=20;
        int num2=15;
        sum(num1,num2);
        difference(num1,num2);
        product(num1,num2);
        quotient(num1,num2);
    }
}