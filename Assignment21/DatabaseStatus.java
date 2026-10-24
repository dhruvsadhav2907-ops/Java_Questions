import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseStatus{
    private static final String URL = "jdbc:mysql://localhost:3306/university_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "29072007.ds";
    public static void main(String[] args) {
        System.out.println("Attempting to connect to the database...");
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("\n================ CONNECTION STATUS ================");
                System.out.println("Status                   : CONNECTED (Active)");
                System.out.println("Is Closed?               : " + conn.isClosed());
                System.out.println("Connection Validity (3s) : " + conn.isValid(3));
                System.out.println("Auto-Commit Mode         : " + conn.getAutoCommit());
                System.out.println("Read-Only Mode           : " + conn.isReadOnly());
                DatabaseMetaData metaData = conn.getMetaData();
                System.out.println("\n----------------- DATABASE DETAILS ----------------");
                System.out.println("Database Product Name    : " + metaData.getDatabaseProductName());
                System.out.println("Database Product Version : " + metaData.getDatabaseProductVersion());
                System.out.println("Driver Name              : " + metaData.getDriverName());
                System.out.println("Driver Version           : " + metaData.getDriverVersion());
                System.out.println("Connected URL            : " + metaData.getURL());
                System.out.println("Current User             : " + metaData.getUserName());
                System.out.println("====================================================");
            }
        } catch (SQLException e) {
            System.out.println("\n================ CONNECTION STATUS ================");
            System.out.println("Status : FAILED TO CONNECT");
            System.out.println("Reason : " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("====================================================");
        }
    }
}