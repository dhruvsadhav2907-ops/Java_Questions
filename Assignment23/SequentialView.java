import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class SequentialView{
    private static final String URL = "jdbc:mysql://localhost:3306/company_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "29072007.ds"; 
    public static void main(String[] args) {
        String selectSQL = "SELECT emp_id, name, department, salary FROM employees ORDER BY emp_id ASC";
        Scanner scanner = new Scanner(System.in);
        try (
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectSQL)
        ) {
            System.out.println("==========================================================");
            System.out.println("        SEQUENTIAL EMPLOYEE RECORD VIEWER (ResultSet)     ");
            System.out.println("==========================================================");
            int count = 0;
            while (rs.next()) {
                count++;
                int empId = rs.getInt("emp_id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                double salary = rs.getDouble("salary");
                System.out.println("\n------------------ [ EMPLOYEE #" + count + " ] ------------------");
                System.out.printf("%-15s : %d%n", "Employee ID", empId);
                System.out.printf("%-15s : %s%n", "Name", name);
                System.out.printf("%-15s : %s%n", "Department", department);
                System.out.printf("%-15s : ₹%.2f%n", "Salary", salary);
                System.out.printf("%-15s : Row %d%n", "Cursor Position", rs.getRow());
                System.out.println("---------------------------------------------------------");
                System.out.print("Press [Enter] for next employee (or type 'q' to quit): ");
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("q")) {
                    System.out.println("\nSequential retrieval stopped by user.");
                    break;
                }
            }
            if (count == 0) {
                System.out.println("\nNo employee records found in the table.");
            } else if (rs.isAfterLast()) {
                System.out.println("\n[END OF RECORDS] All employee entries have been viewed.");
            }
        } catch (SQLException e) {
            System.err.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}