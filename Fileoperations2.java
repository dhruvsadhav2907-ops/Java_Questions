import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
class Employee{
        int emp_id;
        String name;
        String department;
        char gender;
        double salary; 

        public Employee(int emp_id, String name, String department, char gender,double salary){
            this.emp_id=emp_id;
            this.name=name;
            this.department=department;
            this.gender=gender;
            this.salary=salary;
        }

        public String Convert(){
            String new_str = emp_id + "," + name + "," + department + "," + gender + "," + salary + "\n";
            return new_str;
        }        
}


public class Fileoperations2{
    public static void main(String[] args) {
        try{
            Employee e1 = new Employee(101,"Rohit","CSE",'M',25000.00);
            Employee e2 = new Employee(102,"Raman","AI/ML",'M',30000.00);
            FileWriter writer = new FileWriter("Employees.txt");
            writer.write(e1.Convert());
            writer.write(e2.Convert());
            writer.close();

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        try {
            FileReader reader = new FileReader("Employees.txt");
            int ch;
            while((ch=reader.read())!=-1){
                System.out.println((char) ch);
                
            }
            reader.close();
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        try(Scanner sc = new Scanner(new File("Employees.txt"))){
            while(sc.hasNext()){
                String word = sc.next();
                System.out.println(word);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}