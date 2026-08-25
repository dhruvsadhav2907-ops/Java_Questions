import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

public class DataStructuresDemo { // Renamed class to avoid shadowing java.util.Arrays

    public static void main(String[] args) {

        // --- ArrayList (Strings) ---
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Strawyberry");
        fruits.add("Orange");
        fruits.add("cherry");

        System.out.println("Original dynamic array :" + fruits);
        fruits.remove("cherry");
        System.out.println("Modified dynamic array :" + fruits);
        fruits.remove("Banana");
        System.out.println("Original dynamic array :" + fruits);

        fruits.remove("Apple");
        fruits.remove("Strawyberry");
        fruits.remove("Orange");
        System.out.println("Aftermath dynamic array :" + fruits);

        // --- ArrayList (Integers) ---
        ArrayList<Integer> patterns = new ArrayList<>();
        patterns.add(2); // index 0
        patterns.add(4); // index 1
        patterns.add(5); // index 2
        patterns.add(9); // index 3
        patterns.add(1); // index 4
        patterns.add(7); // index 5
        patterns.add(8); // index 6

        System.out.println("\nInitial Patterns: " + patterns);
        patterns.remove(2); // Removes element at index 2 (value 5)
        patterns.remove(4); // Removes element at index 4 (value 7)
        System.out.println("Patterns after removals: " + patterns);

        // --- Vector (Fixed variable references) ---
        Vector<Integer> numbers = new Vector<>();
        numbers.add(100); // FIXED: Adding to numbers, not patterns
        numbers.add(200);
        numbers.add(300);

        System.out.println("\nVector :" + numbers);
        numbers.remove(1); // FIXED: Removing from numbers
        System.out.println("After removing index 1 :" + numbers);
        System.out.println("Size : " + numbers.size());
        System.out.println("Element at index 0: " + numbers.get(0)); // Works properly!

        // --- String Methods ---
        String str = "      Java Programming        ";
        System.out.println("\nLength :" + str.length());
        System.out.println("Trimmed: " + str.trim());
        System.out.println("Uppercase: " + str.toUpperCase());
        System.out.println("Substring(2,6): " + str.substring(2, 6));
        System.out.println("Replaced Java with Python :" + str.replace("Java", "python"));

        // --- StringBuffer ---
        StringBuffer sb = new StringBuffer("hello");
        sb.append("world");
        System.out.println("\nAfter Append: " + sb);
        sb.insert(5, "java"); // Note: "helloworld" inserted at index 5 gives "hellojavaworld"
        System.out.println("After insert: " + sb);
        sb.replace(0, 5, "HI");
        System.out.println("After Replace: " + sb);
        sb.delete(2, 5);
        System.out.println("After Delete : " + sb);
        sb.reverse();
        System.out.println("After reverse: " + sb);

        // --- StringTokenizer ---
        String str2 = "Java,Python,C++,Javascript";
        StringTokenizer st = new StringTokenizer(str2, ",");
        System.out.println("\nTokens:");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }
}