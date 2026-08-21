interface Printable{
    void printDetails();
}

class Student implements Printable{
    String name;
    int rollNumber;
    String course;
    int division;

    Student(String name,int rollNumber,String course,int division){
        this.name=name;
        this.rollNumber=rollNumber;
        this.course=course;
        this.division=division;
    }
    public void printDetails(){
        System.out.println("Student Name : "+name);
        System.out.println("Student's Roll Number : "+rollNumber);
        System.out.println("Course enrolled by Student : "+course);
        System.out.println("Student's Division : "+division);
    }
}
class Employee implements Printable{
    String name;
    int empID;
    String dept;
    int salary;

    Employee(String name,int empId,String dept,int salary){
        this.name=name;
        this.empID=empID;
        this.dept=dept;
        this.salary=salary;
    }

    public void printDetails(){
        System.out.println("Employee Name : "+name);
        System.out.println("Employee ID : "+empID);
        System.out.println("Employee's Department : "+dept);
        System.out.println("Employee's Salary : "+salary);
    }
}
public class InterfaceExample{
    public static void main(String[] args) {
     Student s1 = new Student("Vaishali Kharnal",20,"B.Sc.",1);
     Employee e1 = new Employee("Varsha Gupta",101,"Physics",45000);
     System.out.println("\n");
     System.out.println("Student Details :");  
     s1.printDetails();
     System.out.println("\n");
     System.out.println("Employee Details : ");
     e1.printDetails();  
    }
}