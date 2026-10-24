import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JDBCExplorationDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/lab_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "29072007.ds"; 
    public static void main(String[] args) {
        System.out.println("         JDBC CORE CLASSES & INTERFACES EXPLORATION       ");
        System.out.println("[1] EXPLORING: java.sql.DriverManager (Class)");
        System.out.println("Role: Factory class that manages registered JDBC drivers and creates physical connections.");
        System.out.println("Login Timeout: " + DriverManager.getLoginTimeout() + " seconds");
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("\n[2] EXPLORING: java.sql.Connection (Interface)");
            System.out.println("Role: Represents an active session/socket between Java and the database engine.");
            System.out.println("Runtime Implementation Class: " + conn.getClass().getName());
            System.out.println("Is Connection Closed? " + conn.isClosed());
            System.out.println("Auto-Commit Mode: " + conn.getAutoCommit());
            System.out.println("Transaction Isolation Level: " + conn.getTransactionIsolation());
            DatabaseMetaData dbMeta = conn.getMetaData();
            System.out.println("Connected DBMS: " + dbMeta.getDatabaseProductName() + " " + dbMeta.getDatabaseProductVersion());
            System.out.println("JDBC Driver: " + dbMeta.getDriverName() + " (v" + dbMeta.getDriverVersion() + ")");
            try (Statement stmt = conn.createStatement()) {
                System.out.println("\n[3] EXPLORING: java.sql.Statement (Interface)");
                System.out.println("Role: Used for executing static SQL statements and returning generated results.");
                System.out.println("Runtime Implementation Class: " + stmt.getClass().getName());
                System.out.println("Query Timeout: " + stmt.getQueryTimeout() + " seconds");
                System.out.println("Max Rows Allowed: " + (stmt.getMaxRows() == 0 ? "Unlimited" : stmt.getMaxRows()));
                String insertQuery = "INSERT INTO system_logs (module, status) VALUES ('AUTH_SERVICE', 'ACTIVE')";
                int rowsAffected = stmt.executeUpdate(insertQuery);
                System.out.println("\nExecuting stmt.executeUpdate() -> Rows Affected: " + rowsAffected);
                String selectQuery = "SELECT log_id, module, status FROM system_logs";
                try (ResultSet rs = stmt.executeQuery(selectQuery)) {
                    System.out.println("Executing stmt.executeQuery() -> Retrieved Results:");
                    System.out.println("----------------------------------------------------------");
                    System.out.printf("%-10s | %-20s | %-15s%n", "Log ID", "Module", "Status");
                    System.out.println("----------------------------------------------------------");
                    while (rs.next()) {
                        System.out.printf("%-10d | %-20s | %-15s%n",rs.getInt("log_id"),rs.getString("module"),rs.getString("status"));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("JDBC Error Encountered: " + e.getMessage());
            e.printStackTrace();
        }
    }
}