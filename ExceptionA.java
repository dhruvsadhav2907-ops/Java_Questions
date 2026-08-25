public class ExceptionA {
    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 0;
        String s = null;

        try {
            try {
                int r = num1 / num2;
            } catch (ArithmeticException e) {
                System.out.println("Divide by zero exception!!!");
                System.out.println("Message: " + e.getMessage());
            }

            // Triggers NullPointerException
            System.out.println(s.length());

        } catch (NullPointerException | ArithmeticException e) {
            System.out.println("Caught in multi-catch block:");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Type : " + e.getClass().getSimpleName() + " occurred");
        } catch (Exception e) {
            System.out.println("Some other error occurred");
        } finally {
            System.out.println("Inside finally");
        }

        System.out.println("Program continues");
    }
}