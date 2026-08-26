import java.util.Scanner;

public class ATM{ 
    public static void main(String[] args) {
    
        double balance=500000.00;
        double deposit_amount;
        double withdrawal_amount;

        Scanner sc = new Scanner(System.in);
        System.out.println("Available balance : "+balance);
        System.out.println("Enter amount to withdraw");

        try {
            withdrawal_amount = sc.nextDouble();
            if(withdrawal_amount<=0){
                throw new ArithmeticException("Amount must be greater than 0 ");
            }
            if(withdrawal_amount>balance){
                throw new ArithmeticException("Insufficient balance you only have : "+balance);
            }
            balance-=withdrawal_amount;
            System.out.println("Withdrawal Successful. your current balance is : "+balance);
        } catch (ArithmeticException e) {
            System.out.println("An error occured : "+e.getMessage());
        } catch(Exception e){
            System.out.println("inValid input. Please enter a valid number");
        } finally{
            sc.close();
            System.out.println("Thank you!");
        }
    }

}