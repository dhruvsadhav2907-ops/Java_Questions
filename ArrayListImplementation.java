import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

public class ArrayListImplementation {
    
    public static void main(String[] args) {
        
        ArrayList<String> fruitsList = new ArrayList<>();
        fruitsList.add("Banana");
        fruitsList.add("Apple");
        fruitsList.add("Strawberry");
        fruitsList.add("Orange");
        fruitsList.add("Cherry");
        
        System.out.println("Original dynamic array : " + fruitsList);
        fruitsList.remove("Cherry");
        System.out.println("Modified dynamic array : " + fruitsList);
        fruitsList.remove("Banana");
        System.out.println("Updated dynamic array  : " + fruitsList);
        
        fruitsList.remove("Apple");
        fruitsList.remove("Strawberry");
        fruitsList.remove("Orange");
        System.out.println("Aftermath dynamic array: " + fruitsList);
        
        ArrayList<Integer> patternList = new ArrayList<>();
        patternList.add(2);
        patternList.add(4);
        patternList.add(5);
        patternList.add(9);
        patternList.add(1);
        patternList.add(7);
        patternList.add(8);
        
        System.out.println("Array list of integers : " + patternList);
        patternList.remove(2);
        patternList.remove(4);
        System.out.println("Modified Array list    : " + patternList);

        Vector<Integer> numberVector = new Vector<>();
        numberVector.add(100);
        numberVector.add(200);
        numberVector.add(300);
        
        System.out.println("Vector                 : " + numberVector);
        numberVector.remove(1);
        System.out.println("After removing index 1 : " + numberVector);
        System.out.println("Size                   : " + numberVector.size());
        System.out.println("Element at index 0     : " + numberVector.get(0));

        String rawString = "       Java Programming        ";
        System.out.println("Length                 : " + rawString.length());
        System.out.println("Trimmed                : " + rawString.trim());
        System.out.println("Uppercase              : " + rawString.toUpperCase());
        System.out.println("Substring(7,11)        : " + rawString.substring(7, 11));
        System.out.println("Replaced Java          : " + rawString.replace("Java", "python"));

        StringBuffer stringBuffer = new StringBuffer("hello");
        stringBuffer.append("world");
        System.out.println("After Append           : " + stringBuffer);
        stringBuffer.insert(6, "java");
        System.out.println("After Insert           : " + stringBuffer);
        stringBuffer.replace(0, 5, "HI");
        System.out.println("After Replace          : " + stringBuffer);
        stringBuffer.delete(2, 5);
        System.out.println("After Delete           : " + stringBuffer);
        stringBuffer.reverse();
        System.out.println("After Reverse          : " + stringBuffer);

        String csvData = "Java,Python,C++,Javascript";
        StringTokenizer tokenizer = new StringTokenizer(csvData, ",");
        
        while (tokenizer.hasMoreTokens()) {
            System.out.println(tokenizer.nextToken());
        }
    }
}