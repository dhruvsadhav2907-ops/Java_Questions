class Employee{
    String name="Harish Chouhan";
    int age = 23;
    int empid = 101;
    float salary = 1500000f;
    String Position = "Employee";
    public void showEmployeeDetails(){
        System.out.println("Name : "+name);
        System.out.println("Age : "+age);
        System.out.println("Employee ID : "+empid);
        System.out.println("Salary : "+salary);
        System.out.println("Position: "+Position);
    }
}

class Manager extends Employee{
    String name = "Vaishali Singh";
    int age = 32;
    int empid = 204;
    float salary = 150000000000f;
    String position = "Manager";
    public void showManagerDetails(){
        System.out.println("Name : "+name);
        System.out.println("Age : "+age);
        System.out.println("Employee ID : "+empid);
        System.out.println("Salary : "+salary);
        System.out.println("Position: "+Position);
    }
    public void showEmpDetails(){
        super.showEmployeeDetails();
    }
}

public class EmployeeSuperKeyword{
    public static void main(String[] args){
        Manager m1 = new Manager();
        System.out.println("Employee Details are : ");
        m1.showEmpDetails();
        System.out.println(" ");
        System.out.println("Manager Details are : ");
        m1.showManagerDetails();
    }
}