import java.util.ArrayList;
import java.util.Scanner;

public class Registration {

    
    private static ArrayList<String> courseList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n1. Add Course");
            System.out.println("2. Remove Course");
            System.out.println("3. Display Registered Courses");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.print("Enter Course Code/Name to add: ");
                    String courseToAdd = scanner.nextLine().trim();
                    addCourse(courseToAdd);
                    break;

                case "2":
                    System.out.print("Enter Course Code/Name to remove: ");
                    String courseToRemove = scanner.nextLine().trim();
                    removeCourse(courseToRemove);
                    break;

                case "3":
                    displayCourses();
                    break;

                case "4":
                    running = false;
                    System.out.println("\nThank you for using the Course Registration System!");
                    break;

                default:
                    System.out.println("Invalid selection! Please enter a number between 1 and 4.");
            }
        }
        scanner.close();
    }
    public static void addCourse(String courseName) {
        if (courseName.isEmpty()) {
            System.out.println("Error: Course name cannot be empty.");
            return;
        }
        for (String c : courseList) {
            if (c.equalsIgnoreCase(courseName)) {
                System.out.println("Notice: You are already registered for '" + courseName + "'.");
                return;
            }
        }
        courseList.add(courseName);
        System.out.println("SUCCESS: '" + courseName + "' registered successfully.");
    }
    public static void removeCourse(String courseName) {
        if (courseList.isEmpty()) {
            System.out.println("Notice: No courses registered yet.");
            return;
        }
        boolean foundAndRemoved = courseList.removeIf(c -> c.equalsIgnoreCase(courseName));
        if (foundAndRemoved) {
            System.out.println("SUCCESS: '" + courseName + "' removed successfully.");
        } else {
            System.out.println("Error: Course '" + courseName + "' not found in your registrations.");
        }
    }
    public static void displayCourses() {
        StringBuffer displayBuffer = new StringBuffer();        
        displayBuffer.append(" Total Registered: ").append(courseList.size()).append("\n");
        if (courseList.isEmpty()) {
            displayBuffer.append(" No courses currently registered.\n");
        } else {
            for (int i = 0; i < courseList.size(); i++) {
                displayBuffer.append(courseList.get(i)).append("\n");
            }
        }
        System.out.println(displayBuffer.toString());
    }
}