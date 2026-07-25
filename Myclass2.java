class Student{
    int prn;
    String name;
    String email;
    String dept;
    char gender;
    float gpa;

    Student(){
        prn=101;
        name="Harsh Agrawal";
        email="Harsh@gmail.com";
        dept=" Mech Eng.";
        gender='M';
        gpa=6.85f;
        System.out.println("Inside Default constructor");
    }

    public Student(int p, String n, String e, String d, char ge, float g) {
        prn = p;
        name = n;
        email = e;
        dept = d;
        gender = ge;
        gpa = g;
        System.out.println("Inside Parameterized Constructor");
    }
    void display(){
        System.out.println("Name : "+name);
        System.out.println("PRN :"+prn);
        System.out.println("Email :"+email);
        System.out.println("Department :"+dept);
        System.out.println("Gender :"+gender);
        System.out.println("GPA :"+gpa);
    }
    
}
public class Myclass2{
    public static void main(String[] args) {
        Student S1= new Student();
        S1.display();
        Student S2 = new Student(114,"Dhruv","Dhruv@gmail.com","CSE",'M',7.84f);
        S2.display();
    }
}