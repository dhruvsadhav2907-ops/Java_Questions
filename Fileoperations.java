import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
public class Fileoperations{
    public static void main(String[] args){
        try{
           FileWriter writer = new FileWriter("data.txt");
           writer.write("Welcome to java file handling. \n");
           writer.write("This is an example of writing and reading a file. ");
           writer.close();

           FileReader reader = new FileReader("data.txt");
           int ch;
           System.out.println("Contents of the file : ");
           while((ch= reader.read())!=-1){
            System.out.println((char)ch);
           } 
           reader.close();
        } 
        catch (Exception e) {
            System.out.println("An error occured"+e.getMessage());
        }
        try(Scanner sc = new Scanner(new File("data.txt"))){
            while(sc.hasNext()){
                String word = sc.next();
                System.out.println(word);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}