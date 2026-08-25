import java.util.Scanner;


public class WrapperApplication{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        // 1. Accepting raw inputs from the user as Strings
        System.out.print("Enter Employee ID : ");
        String id = scanner.nextLine();

        System.out.print("Enter Basic Salary: ");
        String Sal = scanner.nextLine();

        System.out.print("Enter Bonus Amount: ");
        String bonus = scanner.nextLine();

        // 2. Converting String values directly into Wrapper Objects
        Integer empIdObj = Integer.valueOf(id);
        Double basicSalaryObj = Double.valueOf(Sal);
        Double bonusObj = Double.valueOf(bonus);

        System.out.println("\n--- Performing Validation ---");

        // 3. Validation operations using Double wrapper methods
        // Double.compare(a, b) returns > 0 if a > b, 0 if equal, and < 0 if a < b
        boolean isSalaryValid = Double.compare(basicSalaryObj, 0.0) > 0;
        boolean isBonusValid = Double.compare(bonusObj, 0.0) >= 0;

        if (!isSalaryValid) {
            System.out.println("Validation Failed: Basic Salary must be greater than 0.");
        } else if (!isBonusValid) {
            System.out.println("Validation Failed: Bonus cannot be negative.");
        } else {
            System.out.println("Validation Successful: All entered financial values are valid.");

            // 4. Calculating Tax (10% of Basic) and Net Salary
            Double taxDeductionObj = basicSalaryObj * 0.10; // Autoboxing to Double object
            Double netSalaryObj = basicSalaryObj + bonusObj - taxDeductionObj;

            // 5. Displaying Payroll Summary
            
            System.out.println("Employee ID     : " + empIdObj);
            System.out.println("Basic Salary    : ₹" + String.format("%.2f", basicSalaryObj));
            System.out.println("Bonus Amount    : ₹" + String.format("%.2f", bonusObj));
            System.out.println("Tax Deduction   : ₹" + String.format("%.2f", taxDeductionObj) + " (10%)");
            System.out.println("Net Salary      : ₹" + String.format("%.2f", netSalaryObj));
        }

        scanner.close();
    }
}