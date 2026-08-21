public class WrapperClassDemo {

    public static void main(String[] args) {
        int x = 45;
        double y = 98.6;

        Integer wrappedInt = x; 
        Double wrappedDouble = y;

        System.out.println("Autoboxed Integer Object : " + wrappedInt);
        System.out.println("Autoboxed Double Object  : " + wrappedDouble);


        int unboxedInt = wrappedInt;
        double unboxedDouble = wrappedDouble;

        System.out.println("Unboxed int primitive    : " + unboxedInt);
        System.out.println("Unboxed double primitive : " + unboxedDouble);

        
        

        String str1 = "250";
        String str2 = "70.12";
        String str3 = "true";

        
        int parsedInt = Integer.parseInt(str1);
        double parsedDouble = Double.parseDouble(str2);
        boolean parsedBool = Boolean.parseBoolean(str3);

        System.out.println("Parsed String to int    : " + (parsedInt + 50)); 
        System.out.println("Parsed String to double : " + parsedDouble);
        System.out.println("Parsed String to boolean: " + parsedBool);


        int num1 = 20;
        int num2 = 35;
        System.out.println("Max of " + num1 + " and " + num2 + " : " + Integer.max(num1, num2));
        System.out.println("Binary string of " + num1 + "   : " + Integer.toBinaryString(num1));
        System.out.println("Hex string of " + num1 + "      : " + Integer.toHexString(num1));

        char ch = 'a';
        System.out.println("Is '" + ch + "' a digit?     : " + Character.isDigit(ch));
        System.out.println("Is '" + ch + "' letter?    : " + Character.isLetter(ch));
        System.out.println("Uppercase of '" + ch + "'   : " + Character.toUpperCase(ch));

        
        Integer score = 100;
        double scoreAsDouble = score.doubleValue(); 
        byte scoreAsByte = score.byteValue();       

        System.out.println("Integer value  : " + score);
        System.out.println("As doubleValue : " + scoreAsDouble);
        System.out.println("As byteValue   : " + scoreAsByte);
    }
}