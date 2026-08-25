import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;
public class Arrays{
    
    public static void main(String[] args){
    /*int Arr[] ={1,2,3,4,5,6};
    for(int num :Arr){
        System.out.println(num);
    }
    int i;
    for(i=0;i<Arr.length;i++){
        System.out.println(Arr[i]);
    }*/
    ArrayList<String> fruits= new ArrayList<>();
    fruits.add("Banana");
    fruits.add("Apple");
    fruits.add("Strawyberry");
    fruits.add("Orange");
    fruits.add("cherry");
    System.out.println("Original dynamic array :"+fruits);
    fruits.remove("cherry");
    System.out.println("Modified dynamic array :"+fruits);
    fruits.remove("Banana");
    System.out.println("Original dynamic array :"+fruits);
    
    fruits.remove("Apple");
    fruits.remove("Strawyberry");
    fruits.remove("Orange");
    System.out.println("Aftermath dynamic array :"+fruits);
    ArrayList<Integer> patterns= new ArrayList<>();
    patterns.add(2);
    patterns.add(4);
    patterns.add(5);
    patterns.add(9);
    patterns.add(1);
    patterns.add(7);
    patterns.add(8);
    System.out.println("Array list of integers :"+patterns);
    System.out.println(patterns);
    patterns.remove(2);
    patterns.remove(4);
    System.out.println("Modified Array list :"+patterns);
    System.out.println(patterns);

    Vector<Integer> numbers=new Vector<>();
    numbers.add(100);
    numbers.add(200);
    numbers.add(300);
    System.out.println("Vector :"+numbers);
    numbers.remove(1);
    System.out.println("After removing index 1 :"+numbers);
    System.out.println("Size : "+numbers.size());
    System.out.println("Element at index 0:"+numbers.get(0));
    /*String str1="hello";
    String str2= new String("Java");
    System.out.println(str1);
    System.out.println(str2);
    str1="hi";
    System.out.println(str1);*/
    String str="       Java Programming        ";
    System.out.println("Length :"+str.length());
    System.out.println("Trimmed: "+str.trim());
    System.out.println("Uppercase: "+str.toUpperCase());
    System.out.println("Substring(7,11): "+str.substring(7,11));
    System.out.println("Replaced Java with Python :"+str.replace("Java","python"));
    StringBuffer sb = new StringBuffer("hello");
    sb.append("world");
    System.out.println("After Append: "+sb);
    sb.insert(6,"java");
    System.out.println("after insert: "+sb);
    sb.replace(0,5,"HI");
    System.out.println("After Replace: "+sb);
    sb.delete(2,5);
    System.out.println("After Delete : "+sb);
    sb.reverse();
    System.out.println("After reverse: "+sb);
    String str2="Java,Python,C++,Javascript";
    StringTokenizer st =new StringTokenizer(str2,",");
    while(st.hasMoreTokens()){
        System.out.println(st.nextToken());
    }
    }


}