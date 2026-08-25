import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
class Patient{
    int PID;
    String name;
    int age;
    String diagnosis;
    public Patient(int PID,String name,int age, String diagnosis){
        this.PID=PID;
        this.name=name;
        this.age=age;
        this.diagnosis=diagnosis;
    }
    public String Convert(){
        String new_str= PID + "," + name +","+age+","+diagnosis+"\n";
        return new_str;
    }

}
public class Fileoperations3{
    public static void main(String[] args){
        try {
            Patient p1 = new Patient(101,"Amit",27,"Maleria");
            Patient p2 = new Patient(102,"Suresh",39,"Flu");
            FileWriter writer = new FileWriter("Patients.txt");
            
            writer.write(p1.Convert());
            writer.write(p2.Convert());
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            FileReader reader = new FileReader("Patients.txt");
            int ch;
            while((ch=reader.read())!=-1){
                System.out.println((char) ch);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try (Scanner sc = new Scanner(new File("Patients.txt"))){
            while(sc.hasNext()){
                String word = sc.next();
                System.out.println(word);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        

    }

}