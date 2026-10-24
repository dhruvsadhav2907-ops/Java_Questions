import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class EmployeeCRUDDemo{
    private static final String URL = "jdbc:mysql://localhost:3306/company_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "29072007.ds";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to MySQL database successfully!");
            while (true) {
                System.out.println("\n========== EMPLOYEE MANAGEMENT SYSTEM ==========");
                System.out.println("1. Add Employee (Create)");
                System.out.println("2. Display All Employees (Read)");
                System.out.println("3. Update Employee Salary (Update)");
                System.out.println("4. Delete Employee (Delete)");
                System.out.println("5. Exit");
                System.out.print("Choose an option (1-5): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                switch (choice) {
                    case 1:
                        addEmployee(conn, scanner);
                        break;
                    case 2:
                        displayEmployees(conn);
                        break;
                    case 3:
                        updateEmployeeSalary(conn, scanner);
                        break;
                    case 4:
                        deleteEmployee(conn, scanner);
                        break;
                    case 5:
                        System.out.println("Exiting system. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option! Please enter 1-5.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database Connection Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
    private static void addEmployee(Connection conn, Scanner scanner) {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter department: ");
        String dept = scanner.nextLine();
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();
        String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, dept);
            pstmt.setDouble(3, salary);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("[SUCCESS] Employee added successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error adding employee: " + e.getMessage());
        }
    }
    private static void displayEmployees(Connection conn) {
        String sql = "SELECT emp_id, name, department, salary FROM employees";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.printf("%-8s | %-20s | %-15s | %-10s%n", "ID", "Name", "Department", "Salary (₹)");
            boolean hasRows = false;
            while (rs.next()) {
                hasRows = true;
                System.out.printf("%-8d | %-20s | %-15s | %-10.2f%n",
                        rs.getInt("emp_id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary"));
            }
            if (!hasRows) {
                System.out.println("No employee records found.");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching employees: " + e.getMessage());
        }
    }
    private static void updateEmployeeSalary(Connection conn, Scanner scanner) {
        System.out.print("Enter Employee ID to update: ");
        int empId = scanner.nextInt();
        System.out.print("Enter new salary: ");
        double newSalary = scanner.nextDouble();
        String sql = "UPDATE employees SET salary = ? WHERE emp_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newSalary);
            pstmt.setInt(2, empId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("[SUCCESS] Salary updated successfully!");
            } else {
                System.out.println("[WARNING] No employee found with ID: " + empId);
            }
        } catch (SQLException e) {
            System.err.println("Error updating employee: " + e.getMessage());
        }
    }
    private static void deleteEmployee(Connection conn, Scanner scanner) {
        System.out.print("Enter Employee ID to delete: ");
        int empId = scanner.nextInt();
        String sql = "DELETE FROM employees WHERE emp_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, empId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("[SUCCESS] Employee deleted successfully!");
            } else {
                System.out.println("[WARNING] No employee found with ID: " + empId);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
        }
    }
}