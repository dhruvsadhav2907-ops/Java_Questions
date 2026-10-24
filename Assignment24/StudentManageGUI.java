import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class StudentManageGUI extends JFrame {
    private static final String URL = "jdbc:mysql://localhost:3306/university_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "29072007.ds"; 
    private JTextField txtRollNo, txtName, txtCourse, txtMarks;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnRefresh;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    public StudentManageGUI() {
        setTitle("Student Management System - JDBC Desktop GUI");
        setSize(800, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));

        formPanel.add(new JLabel("Roll Number:"));
        txtRollNo = new JTextField();
        formPanel.add(txtRollNo);

        formPanel.add(new JLabel("Full Name:"));
        txtName = new JTextField();
        formPanel.add(txtName);

        formPanel.add(new JLabel("Course:"));
        txtCourse = new JTextField();
        formPanel.add(txtCourse);

        formPanel.add(new JLabel("Marks:"));
        txtMarks = new JTextField();
        formPanel.add(txtMarks);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAdd = new JButton("Add (Insert)");
        btnUpdate = new JButton("Update Marks");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear Form");
        btnRefresh = new JButton("Refresh Table");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnRefresh);

        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        topContainer.add(formPanel, BorderLayout.CENTER);
        topContainer.add(buttonPanel, BorderLayout.SOUTH);
        add(topContainer, BorderLayout.NORTH);

        String[] columnNames = {"Roll Number", "Full Name", "Course", "Marks"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Read-only table view
            }
        };

        studentTable = new JTable(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Student Database Records"));

        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        tableContainer.add(scrollPane, BorderLayout.CENTER);
        add(tableContainer, BorderLayout.CENTER);

        studentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    txtRollNo.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    txtName.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    txtCourse.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    txtMarks.setText(tableModel.getValueAt(selectedRow, 3).toString());
                    txtRollNo.setEditable(false); // Disallow modifying primary key during edits
                }
            }
        });

        btnAdd.addActionListener(e -> insertStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e -> clearFields());
        btnRefresh.addActionListener(e -> loadStudentData());

        loadStudentData();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private void insertStudent() {
        if (!validateInputs(true)) return;

        String sql = "INSERT INTO student_gui (roll_no, name, course, marks) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Integer.parseInt(txtRollNo.getText().trim()));
            pstmt.setString(2, txtName.getText().trim());
            pstmt.setString(3, txtCourse.getText().trim());
            pstmt.setDouble(4, Double.parseDouble(txtMarks.getText().trim()));

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadStudentData();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding student: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadStudentData() {
        tableModel.setRowCount(0); 
        String sql = "SELECT roll_no, name, course, marks FROM student_gui ORDER BY roll_no ASC";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("roll_no"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getDouble("marks")
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void updateStudent() {
        if (!validateInputs(false)) return;

        String sql = "UPDATE student_gui SET name = ?, course = ?, marks = ? WHERE roll_no = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, txtName.getText().trim());
            pstmt.setString(2, txtCourse.getText().trim());
            pstmt.setDouble(3, Double.parseDouble(txtMarks.getText().trim()));
            pstmt.setInt(4, Integer.parseInt(txtRollNo.getText().trim()));

            int affected = pstmt.executeUpdate();
            if (affected > 0) {
                JOptionPane.showMessageDialog(this, "Student updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadStudentData();
            } else {
                JOptionPane.showMessageDialog(this, "Record not found.", "Warning", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating record: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        String rollText = txtRollNo.getText().trim();
        if (rollText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select or specify a Roll Number to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete Roll Number " + rollText + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        String sql = "DELETE FROM student_gui WHERE roll_no = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Integer.parseInt(rollText));
            int affected = pstmt.executeUpdate();

            if (affected > 0) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadStudentData();
            } else {
                JOptionPane.showMessageDialog(this, "No record found for Roll Number: " + rollText, "Warning", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting record: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtRollNo.setText("");
        txtName.setText("");
        txtCourse.setText("");
        txtMarks.setText("");
        txtRollNo.setEditable(true);
        studentTable.clearSelection();
    }

    private boolean validateInputs(boolean checkRoll) {
        if (checkRoll && txtRollNo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Roll Number cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtName.getText().trim().isEmpty() || txtCourse.getText().trim().isEmpty() || txtMarks.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            if (checkRoll) Integer.parseInt(txtRollNo.getText().trim());
            Double.parseDouble(txtMarks.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Roll Number must be an integer and Marks must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManageGUI().setVisible(true));
    }
}