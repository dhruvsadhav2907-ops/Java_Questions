public class TryCatchExample{
    public static void main(String[] args){
        int num1 = 10;
        int num2 =0 ; 
        try {
            int result = num1/num2;

            System.out.println("Result : "+ result);
        } catch (ArithmeticException e){
            System.out.println("Error: Cannot Divide by Zero");
            System.out.println("Exception message:"+e.getMessage());
        }
        System.out.println("Program continues after exception handling");
    }

}