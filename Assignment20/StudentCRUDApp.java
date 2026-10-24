import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class StudentCRUDApp {
    private static final String URL = "jdbc:mysql://localhost:3306/university_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "29072007.ds"; 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to MySQL database successfully!");
            while (true) {
                System.out.println("\n========== STUDENT RECORD MANAGEMENT SYSTEM ==========");
                System.out.println("1. Add Student (Create)");
                System.out.println("2. View All Students (Read)");
                System.out.println("3. Update Student Marks (Update)");
                System.out.println("4. Delete Student (Delete)");
                System.out.println("5. Exit");
                System.out.print("Enter your choice (1-5): ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        createStudent(conn, scanner);
                        break;
                    case 2:
                        readStudents(conn);
                        break;
                    case 3:
                        updateStudentMarks(conn, scanner);
                        break;
                    case 4:
                        deleteStudent(conn, scanner);
                        break;
                    case 5:
                        System.out.println("Exiting application. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice! Please select between 1 and 5.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
    private static void createStudent(Connection conn, Scanner scanner) {
        System.out.print("Enter Roll Number: ");
        int rollNo = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Course: ");
        String course = scanner.nextLine();
        System.out.print("Enter Marks: ");
        double marks = scanner.nextDouble();
        String insertSQL = "INSERT INTO students2 (roll_number, name, course, marks) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, rollNo);
            pstmt.setString(2, name);
            pstmt.setString(3, course);
            pstmt.setDouble(4, marks);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("[SUCCESS] Student record added successfully.");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Failed to insert student: " + e.getMessage());
        }
    }

    private static void readStudents(Connection conn) {
        String selectSQL = "SELECT roll_number, name, course, marks FROM students2";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            System.out.println("\n----------------------------------------------------------------------");
            System.out.printf("%-12s | %-22s | %-18s | %-8s%n", "Roll No", "Name", "Course", "Marks");
            System.out.println("----------------------------------------------------------------------");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%-12d | %-22s | %-18s | %-8.2f%n",
                        rs.getInt("roll_number"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getDouble("marks"));
            }
            if (!found) {
                System.out.println("No records found in database.");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Failed to fetch student records: " + e.getMessage());
        }
    }
    private static void updateStudentMarks(Connection conn, Scanner scanner) {
        System.out.print("Enter Roll Number to update: ");
        int rollNo = scanner.nextInt();
        System.out.print("Enter New Marks: ");
        double newMarks = scanner.nextDouble();
        String updateSQL = "UPDATE students2 SET marks = ? WHERE roll_number = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setDouble(1, newMarks);
            pstmt.setInt(2, rollNo);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("[SUCCESS] Marks updated successfully.");
            } else {
                System.out.println("[WARNING] No student found with Roll Number: " + rollNo);
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Failed to update marks: " + e.getMessage());
        }
    }
    private static void deleteStudent(Connection conn, Scanner scanner) {
        System.out.print("Enter Roll Number to delete: ");
        int rollNo = scanner.nextInt();
        String deleteSQL = "DELETE FROM students2 WHERE roll_number = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, rollNo);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("[SUCCESS] Student record deleted successfully.");
            } else {
                System.out.println("[WARNING] No student found with Roll Number: " + rollNo);
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Failed to delete student: " + e.getMessage());
        }
    }
}