import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class HospitalStaffLogin {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "29072007.ds"; // Update with your MySQL password
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("         HOSPITAL MANAGEMENT SYSTEM - STAFF LOGIN         ");
        System.out.print("Enter Login ID : ");
        String loginId = scanner.nextLine().trim();
        System.out.print("Enter Password : ");
        String password = scanner.nextLine().trim();
        authenticateStaff(loginId, password);
        scanner.close();
    }
    private static void authenticateStaff(String loginId, String password) {
        String authQuery = "SELECT staff_name, role, department FROM staff WHERE login_id = ? AND password = ?";
        try (
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(authQuery)
        ) {
            pstmt.setString(1, loginId);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("staff_name");
                    String role = rs.getString("role");
                    String department = rs.getString("department");
                    displayAccessDashboard(name, role, department);
                } else {
                    System.out.println("[AUTHENTICATION FAILED] Invalid Login ID or Password.");
                    System.out.println("Access Denied. Please contact the Hospital IT Helpdesk.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static void displayAccessDashboard(String name, String role, String department) {
        System.out.println("[AUTHENTICATION SUCCESSFUL] Identity Verified.");
        System.out.println("Logged In As : " + name + " | Role: " + role + " (" + department + ")");
        switch (role.trim().toLowerCase()) {
            case "doctor":
                System.out.println("ACCESS LEVEL: Medical Practitioner");
                System.out.println("Granted Permissions:");
                System.out.println("  -> View and update Patient Medical Diagnoses");
                System.out.println("  -> Authorize Prescription and Medication Charts");
                System.out.println("  -> Schedule Operating Theatres and Surgeries");
                break;
            case "nurse":
                System.out.println("ACCESS LEVEL: Healthcare Support & Nursing");
                System.out.println("Granted Permissions:");
                System.out.println("  -> Update Patient Vitals & Daily Observations");
                System.out.println("  -> Administer Medication Logs");
                System.out.println("  -> Manage Inpatient Bed Allocations");
                break;
            case "admin":
                System.out.println("ACCESS LEVEL: Hospital Administration");
                System.out.println("Granted Permissions:");
                System.out.println("  -> Manage Inpatient/Outpatient Billing Records");
                System.out.println("  -> Shift Scheduling and Duty Rosters");
                break;
            default:
                System.out.println("ACCESS LEVEL: General Staff");
                System.out.println("Granted Permissions: Read-Only Access to Hospital Portal.");
                break;
        }
        System.out.println("==========================================================");
    }
}