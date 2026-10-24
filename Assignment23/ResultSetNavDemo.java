import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ResultSetNavDemo{
    private static final String URL = "jdbc:mysql://localhost:3306/university_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "29072007.ds";
    public static void main(String[] args) {
        String query = "SELECT rank_no, name, department, score FROM student_ranks ORDER BY rank_no ASC";
        try (
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs = stmt.executeQuery(query)
        ) {
            System.out.println("==================================================================");
            System.out.println("             RESULTSET CURSOR NAVIGATION DEMONSTRATION            ");
            System.out.println("==================================================================\n");
            System.out.println("[1] FORWARD TRAVERSAL (using rs.next()):");
            while (rs.next()) {
                printCurrentRow(rs);
            }
            System.out.println("\n[2] BACKWARD TRAVERSAL (using rs.previous()):");
            while (rs.previous()) {
                printCurrentRow(rs);
            }
            System.out.println("\n[3] DIRECT ENDPOINT NAVIGATION:");
            if (rs.last()) {
                System.out.print("Last Row  -> ");
                printCurrentRow(rs);
            }
            if (rs.first()) {
                System.out.print("First Row -> ");
                printCurrentRow(rs);
            }
            System.out.println("\n[4] ABSOLUTE POSITIONING (using rs.absolute(3)):");
            if (rs.absolute(3)) {
                System.out.print("Row #3    -> ");
                printCurrentRow(rs);
            }
            System.out.println("\n[5] RELATIVE POSITIONING (using rs.relative(-1)):");
            if (rs.relative(-1)) {
                System.out.print("Row #2    -> ");
                printCurrentRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static void printCurrentRow(ResultSet rs) throws SQLException {
        int currentRow = rs.getRow();
        int rank = rs.getInt("rank_no");
        String name = rs.getString("name");
        String dept = rs.getString("department");
        double score = rs.getDouble("score");
        System.out.printf("Row %-2d | Rank: %-2d | Name: %-15s | Dept: %-22s | Score: %-5.1f%n", currentRow, rank, name, dept, score);
    }
}