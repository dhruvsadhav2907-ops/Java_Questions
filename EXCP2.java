class DivideByZeroException extends RuntimeException{
    public DivideByZeroException(String m){
        super(m);
    }
}

public class EXCP2{
    public static void divide(int a, int b) {
        if(b==0){
            throw new DivideByZeroException("Divison by Zero is not allowed");
        }
        System.out.println("Result: "+(a/b));
        
    }

    public static void main(String[] args) {
        try {
            divide(10,0);
        } catch (DivideByZeroException e) {
            System.out.println("Caught Excpetion: "+e.getMessage());
        }
    }
}