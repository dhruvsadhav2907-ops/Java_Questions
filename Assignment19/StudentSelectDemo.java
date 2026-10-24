import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentSelectDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/university_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "29072007.ds"; 
    public static void main(String[] args) {
        String selectQuery = "SELECT student_id, name, course, gpa FROM students";
        try (
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery)
        ) {
            System.out.println("Connection successful!\n");
            System.out.println("------------------------------------------------------------------");
            System.out.printf("%-10s | %-20s | %-20s | %-6s%n", "ID", "Name", "Course", "GPA");
            System.out.println("------------------------------------------------------------------");
            boolean hasRecords = false;
            while (rs.next()) {
                hasRecords = true;
                int id = rs.getInt("student_id");
                String name = rs.getString("name");
                String course = rs.getString("course");
                double gpa = rs.getDouble("gpa");
                System.out.printf("%-10d | %-20s | %-20s | %-6.2f%n", id, name, course, gpa);
            }
            if (!hasRecords) {
                System.out.println("No records found in the table.");
            }
        } catch (SQLException e) {
            System.err.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}