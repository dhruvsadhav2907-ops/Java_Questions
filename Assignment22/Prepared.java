import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
public class Prepared{
    private static final String URL = "jdbc:mysql://localhost:3306/university_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "29072007.ds"; 
    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("       EXPLORATION OF JDBC: PreparedStatement AND ResultSet              ");
        System.out.println("==========================================================================\n");
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("[1] EXPLORING: java.sql.PreparedStatement");
            System.out.println("Role: Precompiled SQL statement offering pre-parsing, parameter substitution,");
            System.out.println("      and protection against SQL Injection vulnerabilities.\n");
            String insertSQL = "INSERT INTO courses (course_code, course_name, credits, fee) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmtInsert = conn.prepareStatement(insertSQL)) {
                System.out.println("Runtime Class: " + pstmtInsert.getClass().getName());
                ParameterMetaData pmd = pstmtInsert.getParameterMetaData();
                System.out.println("Number of Placeholders (?) detected: " + pmd.getParameterCount());
                pstmtInsert.setString(1, "CS105");
                pstmtInsert.setString(2, "Artificial Intelligence");
                pstmtInsert.setInt(3, 4);
                pstmtInsert.setDouble(4, 18000.00);
                int rowsAffected = pstmtInsert.executeUpdate();
                System.out.println("Executed pstmtInsert.executeUpdate() -> Rows inserted: " + rowsAffected);
            } catch (SQLException e) {
                System.out.println("[Notice] Insert skipped (Record may already exist): " + e.getMessage());
            }
            System.out.println("\n--------------------------------------------------------------------------");
            System.out.println("[2] EXPLORING: java.sql.ResultSet");
            System.out.println("Role: Tabular data stream representing a database cursor over rows of results.\n");
            String querySQL = "SELECT course_id, course_code, course_name, credits, fee FROM courses WHERE credits >= ?";
            try (PreparedStatement pstmtSelect = conn.prepareStatement(querySQL)) {
                pstmtSelect.setInt(1, 4);
                try (ResultSet rs = pstmtSelect.executeQuery()) {
                    System.out.println("Runtime Class: " + rs.getClass().getName());
                    System.out.println("Cursor Concurrency Type: " + (rs.getConcurrency() == ResultSet.CONCUR_READ_ONLY ? "CONCUR_READ_ONLY" : "CONCUR_UPDATABLE"));
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    System.out.println("Total Columns Returned: " + columnCount);
                    System.out.println("\n--- Column Metadata Inspection ---");
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.printf("Column %d: %-12s | SQL Type: %-10s | Table: %s%n",i,rsmd.getColumnName(i),rsmd.getColumnTypeName(i),rsmd.getTableName(i));
                    }
                    System.out.println("\n--- Query Results (Credits >= 4) ---");
                    System.out.printf("%-10s | %-12s | %-32s | %-8s | %-10s%n","Course ID", "Code", "Course Name", "Credits", "Fee (INR)");
                    System.out.println("--------------------------------------------------------------------------");
                    while (rs.next()) {
                        int id = rs.getInt("course_id");
                        String code = rs.getString("course_code");
                        String name = rs.getString("course_name");
                        int credits = rs.getInt("credits");
                        double fee = rs.getDouble("fee");
                        System.out.printf("%-10d | %-12s | %-32s | %-8d | %-10.2f%n",id, code, name, credits, fee);
                    }
                    System.out.println("--------------------------------------------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}