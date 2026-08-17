class Bank{
    final int Account_number;
    String Account_holderName;
    int balance;
    Bank(int Account_number,String Account_holderName,int balance){
        this.Account_number = Account_number;
        this.Account_holderName = Account_holderName;
        this.balance = balance;
    }

    void displayBankDetails(){
        System.out.println("Account Number : "+Account_number);
        System.out.println("Name of Account holder :"+Account_holderName);
        System.out.println("Available balance in the account :"+balance);
    }
}
public class BankFinalKeyword{
    public static void main(String[] args){
        Bank b1 = new Bank(19636222,"Rajesh",2500);
        System.out.println(" ");
        System.out.println("The Account Number is final and cannot be changed");
        b1.displayBankDetails();
    }
} 