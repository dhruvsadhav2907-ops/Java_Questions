// 1. Custom Checked Exception
class InvalidAgeException extends java.lang.Exception {
    public InvalidAgeException(String m) {
        super(m);
    }
}

// 2. Custom Unchecked Exception
class DivideByZeroException extends RuntimeException {
    public DivideByZeroException(String m) {
        super(m);
    }
}

// 3. Superclass and Subclass demonstrating Method Overriding with throws & throw
class SuperClass {
    void method() {
        System.out.println("Superclass method executed");
    }
}

class SubClass extends SuperClass {
    @Override
    void method() throws ArithmeticException {
        System.out.println("Subclass method executed");
        throw new ArithmeticException("Exception in SubClass");
    }
}

public class ExceptionAssignment {

    // Demonstrates: throw (re-throwing) and finally
    static void fun() {
        try {
            throw new NullPointerException("demo");
        } catch (NullPointerException e) {
            System.out.println("Caught inside fun");
            throw e; // Re-throwing exception
        } finally {
            System.out.println("Finally block inside fun executed");
        }
    }

    // Demonstrates: throw with checked exception and throws in method signature
    public static void validate(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or above");
        }
        System.out.println("Valid age: " + age);
    }

    // Demonstrates: throw with custom runtime exception
    public static void divide(int a, int b) {
        if (b == 0) {
            throw new DivideByZeroException("Division by Zero is not allowed");
        }
        System.out.println("Result: " + (a / b));
    }

    public static void main(String[] args) {
        // --- Demonstration 1: Re-throw & finally ---
        System.out.println("--- 1. Re-throw & finally ---");
        try {
            fun();
        } catch (NullPointerException e) {
            System.out.println("Caught in main: " + e.getMessage());
        }

        // --- Demonstration 2: throws & Checked Exception ---
        System.out.println("\n--- 2. throws & Checked Exception ---");
        try {
            validate(12);
        } catch (InvalidAgeException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        } finally {
            System.out.println("Finally block after validate executed");
        }

        // --- Demonstration 3: throw & Custom Runtime Exception ---
        System.out.println("\n--- 3. Custom Runtime Exception ---");
        try {
            divide(10, 0);
        } catch (DivideByZeroException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        } finally {
            System.out.println("Finally block after divide executed");
        }

        // --- Demonstration 4: Method Overriding with throws & throw ---
        System.out.println("\n--- 4. Method Overriding & throws ---");
        SuperClass s = new SubClass();
        try {
            s.method();
        } catch (ArithmeticException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        } finally {
            System.out.println("Finally block after overridden method call executed");
        }
    }
}