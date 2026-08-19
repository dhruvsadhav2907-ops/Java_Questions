abstract class Payment{
    int amount;
    int transaction_id;
    Payment(int amount, int transaction_id){
        this.amount = amount;
        this.transaction_id = transaction_id; 
    }
    abstract void PaymentProcess();
    public void Receipt(){
        System.out.println("Amount Paid(in ₹) : "+amount);
        System.out.println("Transaction_id : "+transaction_id);
        System.out.println("Status : Paid Successfully");
    }
} 

class CreditCard extends Payment{
    String CardNumber;
    String CardHolderName;
    private int cvv;
    
    CreditCard(int amount, int transaction_id,String CardNumber, String CardHolderName,int cvv){
        super(amount,transaction_id);
        this.CardNumber=CardNumber;
        this.CardHolderName=CardHolderName;
        this.cvv=cvv;
        
    }
    
    void PaymentProcess(){
        String HiddenNumber = "XXXX-XXXX-XXXX-"+ CardNumber.substring(CardNumber.length()-4);
        System.out.println("Processing Credit Card Payemnt....");
        System.out.println("Name of the CardHolder : "+CardHolderName);
        System.out.println("Card Number : "+HiddenNumber);
    }
}
class UPI extends Payment{
    String UPIid;
    UPI(int amount,int transaction_id,String UPIid){
        super(amount,transaction_id);
        this.UPIid=UPIid;
    }

    void PaymentProcess(){
        System.out.println("Processing payment with UPI....");
        System.out.println("Amount(in ₹) : "+super.amount);
        System.out.println("Transaction Id : "+super.transaction_id);
        System.out.println("Status : Paid");
    }
}
public class PaymentAbstractClass{
    public static void main(String[] args){
        System.out.println(" ");
        System.out.println("Welcome to Payment Gateway System!");
        System.out.println(" ");
        System.out.println("Payment using Credit Card");
        CreditCard c = new CreditCard(2500,191092452,"1234-4829-3821-4892","Shreya Mehta",901);
        c.PaymentProcess();
        System.out.println(" ");
        System.out.println("Payment using UPI");
        UPI u1 = new UPI(3500,831290,"BhavyaArora2910@okSbi");
        u1.PaymentProcess();
    }
}