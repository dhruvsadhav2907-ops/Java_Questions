import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class SingleRecord{
    private static final String URL = "jdbc:mysql://localhost:3306/university_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "29072007.ds";
    public static void main(String[] args) {
        String query = "SELECT student_id, full_name, department, semester, cgpa FROM student_records";
        Scanner scanner = new Scanner(System.in);
        try (
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            System.out.println("==========================================================");
            System.out.println("          STEP-BY-STEP RECORD VIEWER (ResultSet)          ");
            System.out.println("==========================================================");
            int recordCounter = 0;
            while (rs.next()) {
                recordCounter++;   
                int id = rs.getInt("student_id");
                String name = rs.getString("full_name");
                String dept = rs.getString("department");
                int semester = rs.getInt("semester");
                double cgpa = rs.getDouble("cgpa");
                System.out.println("\n----------------- [ RECORD #" + recordCounter + " ] -----------------");
                System.out.println("Student ID    : " + id);
                System.out.println("Full Name     : " + name);
                System.out.println("Department    : " + dept);
                System.out.println("Semester      : " + semester);
                System.out.printf("CGPA          : %.2f%n", cgpa);
                System.out.println("Cursor Row No : " + rs.getRow());
                System.out.println("-------------------------------------------------------");
                System.out.print("Press [Enter] to fetch the next record (or type 'q' to quit): ");
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("q")) {
                    System.out.println("\nTraversal stopped by user.");
                    break;
                }
            }
            if (recordCounter == 0) {
                System.out.println("\nNo student records found in the database.");
            } else if (!rs.isAfterLast() && !rs.isClosed()) {
            } else {
                System.out.println("\n[END OF RECORDS] All rows have been traversed.");
            }
        } catch (SQLException e) {
            System.err.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}