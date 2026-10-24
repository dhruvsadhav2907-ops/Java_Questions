import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LibraryManagement extends JFrame {

    private static final String URL = "jdbc:mysql://localhost:3306/library_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "29072007.ds"; 

    private JTextField txtBookId, txtTitle, txtAuthor, txtGenre, txtPrice, txtTotalCopies, txtAvailableCopies;
    private JTextField txtSearch;
    private JButton btnAdd, btnUpdate, btnDelete, btnIssue, btnReturn, btnClear, btnSearch, btnRefresh;
    private JTable bookTable;
    private DefaultTableModel tableModel;

    public LibraryManagement() {
        setTitle("Library Management System - JDBC Desktop Client");
        setSize(1000, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder("Book Details"));

        formPanel.add(new JLabel("Book ID (Auto-generated):"));
        txtBookId = new JTextField();
        txtBookId.setEditable(false);
        txtBookId.setBackground(new Color(240, 240, 240));
        formPanel.add(txtBookId);

        formPanel.add(new JLabel("Title:"));
        txtTitle = new JTextField();
        formPanel.add(txtTitle);

        formPanel.add(new JLabel("Author:"));
        txtAuthor = new JTextField();
        formPanel.add(txtAuthor);

        formPanel.add(new JLabel("Genre:"));
        txtGenre = new JTextField();
        formPanel.add(txtGenre);

        formPanel.add(new JLabel("Price (₹):"));
        txtPrice = new JTextField();
        formPanel.add(txtPrice);

        formPanel.add(new JLabel("Total Copies:"));
        txtTotalCopies = new JTextField();
        formPanel.add(txtTotalCopies);

        formPanel.add(new JLabel("Available Copies:"));
        txtAvailableCopies = new JTextField();
        formPanel.add(txtAvailableCopies);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
        btnAdd = new JButton("Add Book");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnIssue = new JButton("Issue Book");
        btnReturn = new JButton("Return Book");
        btnClear = new JButton("Clear Form");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnIssue);
        buttonPanel.add(btnReturn);
        buttonPanel.add(btnClear);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 5));
        searchPanel.add(new JLabel("Search by Title / Author:"));
        txtSearch = new JTextField(15);
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

        String[] columnHeaders = {"Book ID", "Title", "Author", "Genre", "Price (₹)", "Total Copies", "Available Copies"};
        tableModel = new DefaultTableModel(columnHeaders, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false; // View only in grid
            }
        };

        bookTable = new JTable(tableModel);
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Library Catalog (Live Inventory)"));

        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        tableContainer.add(scrollPane, BorderLayout.CENTER);
        add(tableContainer, BorderLayout.CENTER);

        bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = bookTable.getSelectedRow();
                if (row != -1) {
                    txtBookId.setText(tableModel.getValueAt(row, 0).toString());
                    txtTitle.setText(tableModel.getValueAt(row, 1).toString());
                    txtAuthor.setText(tableModel.getValueAt(row, 2).toString());
                    txtGenre.setText(tableModel.getValueAt(row, 3).toString());
                    txtPrice.setText(tableModel.getValueAt(row, 4).toString());
                    txtTotalCopies.setText(tableModel.getValueAt(row, 5).toString());
                    txtAvailableCopies.setText(tableModel.getValueAt(row, 6).toString());
                }
            }
        });

        btnAdd.addActionListener(e -> addBook());
        btnUpdate.addActionListener(e -> updateBook());
        btnDelete.addActionListener(e -> deleteBook());
        btnIssue.addActionListener(e -> issueBook());
        btnReturn.addActionListener(e -> returnBook());
        btnClear.addActionListener(e -> clearForm());
        btnSearch.addActionListener(e -> searchBooks());
        btnRefresh.addActionListener(e -> loadBooksData());

        
        loadBooksData();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
    }

    private void addBook() {
        if (!validateInputs()) return;

        String sql = "INSERT INTO books (title, author, genre, price, total_copies, available_copies) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, txtTitle.getText().trim());
            pstmt.setString(2, txtAuthor.getText().trim());
            pstmt.setString(3, txtGenre.getText().trim());
            pstmt.setDouble(4, Double.parseDouble(txtPrice.getText().trim()));

            int total = Integer.parseInt(txtTotalCopies.getText().trim());
            int available = txtAvailableCopies.getText().trim().isEmpty() ? total : Integer.parseInt(txtAvailableCopies.getText().trim());

            if (available > total) {
                JOptionPane.showMessageDialog(this, "Available copies cannot exceed total copies.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            pstmt.setInt(5, total);
            pstmt.setInt(6, available);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book successfully added to the catalog!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            loadBooksData();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void loadBooksData() {
        tableModel.setRowCount(0);
        String sql = "SELECT * FROM books ORDER BY book_id ASC";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getDouble("price"),
                        rs.getInt("total_copies"),
                        rs.getInt("available_copies")
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching records: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void updateBook() {
        if (txtBookId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a book from the table to update.", "Notice", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validateInputs()) return;

        String sql = "UPDATE books SET title = ?, author = ?, genre = ?, price = ?, total_copies = ?, available_copies = ? WHERE book_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, txtTitle.getText().trim());
            pstmt.setString(2, txtAuthor.getText().trim());
            pstmt.setString(3, txtGenre.getText().trim());
            pstmt.setDouble(4, Double.parseDouble(txtPrice.getText().trim()));
            pstmt.setInt(5, Integer.parseInt(txtTotalCopies.getText().trim()));
            pstmt.setInt(6, Integer.parseInt(txtAvailableCopies.getText().trim()));
            pstmt.setInt(7, Integer.parseInt(txtBookId.getText().trim()));

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Book record updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadBooksData();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating book: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteBook() {
        if (txtBookId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a book to delete.", "Notice", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this book record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        String sql = "DELETE FROM books WHERE book_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Integer.parseInt(txtBookId.getText().trim()));
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Book deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadBooksData();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting book: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void issueBook() {
        if (txtBookId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a book to issue.", "Notice", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int bookId = Integer.parseInt(txtBookId.getText().trim());
        String checkSql = "SELECT available_copies FROM books WHERE book_id = ?";
        String updateSql = "UPDATE books SET available_copies = available_copies - 1 WHERE book_id = ? AND available_copies > 0";

        try (Connection conn = getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            checkStmt.setInt(1, bookId);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    int available = rs.getInt("available_copies");
                    if (available <= 0) {
                        JOptionPane.showMessageDialog(this, "No copies currently available for issue!", "Out of Stock", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
            }

            updateStmt.setInt(1, bookId);
            int rows = updateStmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Book issued successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadBooksData();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error issuing book: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void returnBook() {
        if (txtBookId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a book to return.", "Notice", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int bookId = Integer.parseInt(txtBookId.getText().trim());
        String checkSql = "SELECT available_copies, total_copies FROM books WHERE book_id = ?";
        String updateSql = "UPDATE books SET available_copies = available_copies + 1 WHERE book_id = ? AND available_copies < total_copies";

        try (Connection conn = getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            checkStmt.setInt(1, bookId);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    int available = rs.getInt("available_copies");
                    int total = rs.getInt("total_copies");
                    if (available >= total) {
                        JOptionPane.showMessageDialog(this, "All copies are already accounted for in the library.", "Return Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
            }

            updateStmt.setInt(1, bookId);
            int rows = updateStmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Book returned successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadBooksData();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error returning book: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void searchBooks() {
        String keyword = txtSearch.getText().trim();
        if (keyword.isEmpty()) {
            loadBooksData();
            return;
        }

        tableModel.setRowCount(0);
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR genre LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String pattern = "%" + keyword + "%";
            pstmt.setString(1, pattern);
            pstmt.setString(2, pattern);
            pstmt.setString(3, pattern);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                            rs.getInt("book_id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("genre"),
                            rs.getDouble("price"),
                            rs.getInt("total_copies"),
                            rs.getInt("available_copies")
                    });
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Search error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtBookId.setText("");
        txtTitle.setText("");
        txtAuthor.setText("");
        txtGenre.setText("");
        txtPrice.setText("");
        txtTotalCopies.setText("");
        txtAvailableCopies.setText("");
        txtSearch.setText("");
        bookTable.clearSelection();
    }

    private boolean validateInputs() {
        if (txtTitle.getText().trim().isEmpty() || txtAuthor.getText().trim().isEmpty() ||
            txtGenre.getText().trim().isEmpty() || txtPrice.getText().trim().isEmpty() ||
            txtTotalCopies.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "All input fields are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            Double.parseDouble(txtPrice.getText().trim());
            Integer.parseInt(txtTotalCopies.getText().trim());
            if (!txtAvailableCopies.getText().trim().isEmpty()) {
                Integer.parseInt(txtAvailableCopies.getText().trim());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Price must be a valid number, and copies must be integer counts.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagement().setVisible(true));
    }
}