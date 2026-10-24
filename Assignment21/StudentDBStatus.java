import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentDBStatus {
    private static final String URL = "jdbc:mysql://localhost:3306/university_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "29072007.ds"; 
    public static void main(String[] args) {
        System.out.println("Connecting to Student Database...\n");
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("==========================================================");
                System.out.println("   Student database is connected successfully!");
                System.out.println("==========================================================");
                System.out.println("Connection Status       : ACTIVE");
                System.out.println("Ping / Is Valid (3s)    : " + conn.isValid(3));
                System.out.println("Auto-Commit Mode        : " + conn.getAutoCommit());
                DatabaseMetaData metaData = conn.getMetaData();
                System.out.println("Connected DBMS          : " + metaData.getDatabaseProductName() + " (v" + metaData.getDatabaseProductVersion() + ")");
                System.out.println("Database Catalog Name   : " + conn.getCatalog());
                System.out.println("Connected User          : " + metaData.getUserName());
                System.out.println("JDBC Driver             : " + metaData.getDriverName() + " (" + metaData.getDriverVersion() + ")");
                System.out.println("==========================================================");
            }
        } catch (SQLException e) {
            System.err.println("==========================================================");
            System.err.println("   Connection to Student database FAILED!");
            System.err.println("==========================================================");
            System.err.println("Error Message : " + e.getMessage());
            System.err.println("SQL State     : " + e.getSQLState());
            System.err.println("Error Code    : " + e.getErrorCode());
            System.err.println("==========================================================");
        }
    }
}