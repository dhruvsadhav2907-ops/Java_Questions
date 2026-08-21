public class WrapperClass2{

    public static void main(String[] args) {
    
        String maths = "85";
        String Physics = "92.5";
        String Chem = "78";
        String Bio = "88.52";

        // Converting String format to int using Integer.parseInt()
        int math = Integer.parseInt(maths);
        float physics = Float.parseFloat(Physics);
        int chemistry = Integer.parseInt(Chem);
        double Biology = Double.parseDouble(Bio);

        // Displaying parsed marks
        System.out.println("Mathematics Marks : " + math);
        System.out.println("Physics Marks     : " + physics);
        System.out.println("Chemistry Marks   : " + chemistry);
        System.out.println("Biology Marks        : " + Biology);

        // Calculating Total Marks
        double totalMarks = math + physics + chemistry + Biology;

        // Calculating Average Marks
        double averageMarks = totalMarks / 4.0;

        System.out.println("Total Marks Obtained : " + totalMarks + " / 400");
        System.out.println("Average Marks        : " + averageMarks + "%");
        
    }
}