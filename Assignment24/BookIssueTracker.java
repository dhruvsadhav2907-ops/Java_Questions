import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookIssueTracker extends JFrame {

    private static final String URL = "jdbc:mysql://localhost:3306/library_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "29072007.ds"; 

    private JTextField txtIssueId, txtBookId, txtStudentName, txtIssueDate, txtReturnDate;
    private JTextField txtSearch;
    private JButton btnIssue, btnUpdate, btnDelete, btnMarkReturned, btnClear, btnSearch, btnRefresh;
    private JTable issueTable;
    private DefaultTableModel tableModel;

    public BookIssueTracker() {
        setTitle("Book Issue Tracking System - Desktop Client");
        setSize(950, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder("Issue Details"));

        formPanel.add(new JLabel("Issue ID (Auto-generated):"));
        txtIssueId = new JTextField();
        txtIssueId.setEditable(false);
        txtIssueId.setBackground(new Color(240, 240, 240));
        formPanel.add(txtIssueId);

        formPanel.add(new JLabel("Book ID:"));
        txtBookId = new JTextField();
        formPanel.add(txtBookId);

        formPanel.add(new JLabel("Student Name:"));
        txtStudentName = new JTextField();
        formPanel.add(txtStudentName);

        formPanel.add(new JLabel("Issue Date (YYYY-MM-DD):"));
        txtIssueDate = new JTextField(LocalDate.now().toString()); // Defaults to current date
        formPanel.add(txtIssueDate);

        formPanel.add(new JLabel("Return Date (YYYY-MM-DD or leave blank):"));
        txtReturnDate = new JTextField();
        formPanel.add(txtReturnDate);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
        btnIssue = new JButton("Issue Book");
        btnUpdate = new JButton("Update Record");
        btnMarkReturned = new JButton("Mark Returned (Today)");
        btnDelete = new JButton("Delete Record");
        btnClear = new JButton("Clear Form");

        buttonPanel.add(btnIssue);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnMarkReturned);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 5));
        searchPanel.add(new JLabel("Search (Student Name / Book ID):"));
        txtSearch = new JTextField(16);
        btnSearch = new JButton("Search");
        btnRefresh = new JButton("Reset Table");
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        searchPanel.add(btnRefresh);

        JPanel northContainer = new JPanel(new BorderLayout());
        northContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        northContainer.add(formPanel, BorderLayout.NORTH);
        northContainer.add(buttonPanel, BorderLayout.CENTER);
        northContainer.add(searchPanel, BorderLayout.SOUTH);
        add(northContainer, BorderLayout.NORTH);

        String[] columnHeaders = {"Issue ID", "Book ID", "Student Name", "Issue Date", "Return Date", "Status"};
        tableModel = new DefaultTableModel(columnHeaders, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false; // Prevent inline editing
            }
        };

        issueTable = new JTable(tableModel);
        issueTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(issueTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Issue Records"));

        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        tableContainer.add(scrollPane, BorderLayout.CENTER);
        add(tableContainer, BorderLayout.CENTER);

        issueTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = issueTable.getSelectedRow();
                if (row != -1) {
                    txtIssueId.setText(tableModel.getValueAt(row, 0).toString());
                    txtBookId.setText(tableModel.getValueAt(row, 1).toString());
                    txtStudentName.setText(tableModel.getValueAt(row, 2).toString());
                    txtIssueDate.setText(tableModel.getValueAt(row, 3).toString());
                    Object retVal = tableModel.getValueAt(row, 4);
                    txtReturnDate.setText(retVal != null ? retVal.toString() : "");
                }
            }
        });

        btnIssue.addActionListener(e -> issueBook());
        btnUpdate.addActionListener(e -> updateRecord());
        btnMarkReturned.addActionListener(e -> markAsReturned());
        btnDelete.addActionListener(e -> deleteRecord());
        btnClear.addActionListener(e -> clearForm());
        btnSearch.addActionListener(e -> searchRecords());
        btnRefresh.addActionListener(e -> loadIssueData());

        loadIssueData();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
    }

    private void issueBook() {
        if (!validateFormInputs()) return;

        String sql = "INSERT INTO book_issues (book_id, student_name, issue_date, return_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Integer.parseInt(txtBookId.getText().trim()));
            pstmt.setString(2, txtStudentName.getText().trim());
            pstmt.setDate(3, Date.valueOf(txtIssueDate.getText().trim()));

            String returnText = txtReturnDate.getText().trim();
            if (returnText.isEmpty()) {
                pstmt.setNull(4, Types.DATE);
            } else {
                pstmt.setDate(4, Date.valueOf(returnText));
            }

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book issued successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            loadIssueData();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadIssueData() {
        tableModel.setRowCount(0);
        String sql = "SELECT issue_id, book_id, student_name, issue_date, return_date FROM book_issues ORDER BY issue_id DESC";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Date returnDate = rs.getDate("return_date");
                String status = (returnDate == null) ? "ISSUED" : "RETURNED";

                tableModel.addRow(new Object[]{
                        rs.getInt("issue_id"),
                        rs.getInt("book_id"),
                        rs.getString("student_name"),
                        rs.getDate("issue_date"),
                        returnDate != null ? returnDate.toString() : "Not Returned",
                        status
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading records: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateRecord() {
        if (txtIssueId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a record from the table to update.", "Notice", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validateFormInputs()) return;

        String sql = "UPDATE book_issues SET book_id = ?, student_name = ?, issue_date = ?, return_date = ? WHERE issue_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Integer.parseInt(txtBookId.getText().trim()));
            pstmt.setString(2, txtStudentName.getText().trim());
            pstmt.setDate(3, Date.valueOf(txtIssueDate.getText().trim()));

            String returnText = txtReturnDate.getText().trim();
            if (returnText.isEmpty()) {
                pstmt.setNull(4, Types.DATE);
            } else {
                pstmt.setDate(4, Date.valueOf(returnText));
            }

            pstmt.setInt(5, Integer.parseInt(txtIssueId.getText().trim()));

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Record updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadIssueData();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating record: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void markAsReturned() {
        if (txtIssueId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select an issued record to mark as returned.", "Notice", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "UPDATE book_issues SET return_date = ? WHERE issue_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, Date.valueOf(LocalDate.now()));
            pstmt.setInt(2, Integer.parseInt(txtIssueId.getText().trim()));

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Book marked as returned today!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadIssueData();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating return status: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteRecord() {
        if (txtIssueId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a record from the table to delete.", "Notice", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this issue record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        String sql = "DELETE FROM book_issues WHERE issue_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Integer.parseInt(txtIssueId.getText().trim()));

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Record deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadIssueData();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting record: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchRecords() {
        String query = txtSearch.getText().trim();
        if (query.isEmpty()) {
            loadIssueData();
            return;
        }

        tableModel.setRowCount(0);
        String sql = "SELECT issue_id, book_id, student_name, issue_date, return_date FROM book_issues " +
                     "WHERE student_name LIKE ? OR CAST(book_id AS CHAR) LIKE ? ORDER BY issue_id DESC";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String pattern = "%" + query + "%";
            pstmt.setString(1, pattern);
            pstmt.setString(2, pattern);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Date returnDate = rs.getDate("return_date");
                    String status = (returnDate == null) ? "ISSUED" : "RETURNED";

                    tableModel.addRow(new Object[]{
                            rs.getInt("issue_id"),
                            rs.getInt("book_id"),
                            rs.getString("student_name"),
                            rs.getDate("issue_date"),
                            returnDate != null ? returnDate.toString() : "Not Returned",
                            status
                    });
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Search Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtIssueId.setText("");
        txtBookId.setText("");
        txtStudentName.setText("");
        txtIssueDate.setText(LocalDate.now().toString());
        txtReturnDate.setText("");
        txtSearch.setText("");
        issueTable.clearSelection();
    }

    private boolean validateFormInputs() {
        if (txtBookId.getText().trim().isEmpty() || txtStudentName.getText().trim().isEmpty() || txtIssueDate.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Book ID, Student Name, and Issue Date are mandatory.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            Integer.parseInt(txtBookId.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Book ID must be an integer.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            LocalDate issueD = LocalDate.parse(txtIssueDate.getText().trim());
            String retStr = txtReturnDate.getText().trim();
            if (!retStr.isEmpty()) {
                LocalDate retD = LocalDate.parse(retStr);
                if (retD.isBefore(issueD)) {
                    JOptionPane.showMessageDialog(this, "Return Date cannot be earlier than Issue Date.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dates must follow the format YYYY-MM-DD.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookIssueTracker().setVisible(true));
    }
}