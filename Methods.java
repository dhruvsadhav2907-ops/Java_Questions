public class Methods{
    public static int add(int a,int b){
        return a+b;
    }
    public static int subtract(int a, int b){
        return a-b;
    }
    public static int multiply(int a, int b){
        return a*b;
    }
    public static int divide(int a, int b){
        return a/b;
    }
    public static void main(String[] args){
        int a=30;
        int b=15;
        System.out.println("The sum is :"+add(a,b));
        System.out.println("The difference is :"+subtract(a,b));
        System.out.println("The product is :"+multiply(a,b));
        System.out.println("The quotient is :"+divide(a,b));

    }

}