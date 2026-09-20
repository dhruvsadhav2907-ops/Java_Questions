import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EmployeeJavaSwing extends JFrame implements ActionListener {

    // Form input components
    private JTextField tfEmpId, tfName, tfSalary;
    private JComboBox<String> cbDepartment;
    private JButton btnSubmit, btnClear;

    public EmployeeJavaSwing() {
        // Frame properties
        setTitle("Employee Registration Form");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout(10, 10));

        // 1. Header label
        JLabel lblHeader = new JLabel("Employee Registration", JLabel.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 16));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        add(lblHeader, BorderLayout.NORTH);

        // 2. Form panel using GridLayout
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 12));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        // Employee ID
        formPanel.add(new JLabel("Employee ID:"));
        tfEmpId = new JTextField();
        formPanel.add(tfEmpId);

        // Employee Name
        formPanel.add(new JLabel("Employee Name:"));
        tfName = new JTextField();
        formPanel.add(tfName);

        // Department (Dropdown)
        formPanel.add(new JLabel("Department:"));
        String[] departments = {"Select Department", "CSE", "AI/ML", "IT", "HR", "Finance"};
        cbDepartment = new JComboBox<>(departments);
        formPanel.add(cbDepartment);

        // Salary
        formPanel.add(new JLabel("Salary (₹):"));
        tfSalary = new JTextField();
        formPanel.add(tfSalary);

        add(formPanel, BorderLayout.CENTER);

        // 3. Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnSubmit = new JButton("Submit");
        btnClear = new JButton("Clear");

        btnSubmit.addActionListener(this);
        btnClear.addActionListener(this);

        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnClear);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            String empId = tfEmpId.getText().trim();
            String name = tfName.getText().trim();
            String department = (String) cbDepartment.getSelectedItem();
            String salaryStr = tfSalary.getText().trim();

            // Form validation
            if (empId.isEmpty() || name.isEmpty() || salaryStr.isEmpty() || department.equals("Select Department")) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields before submitting.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // Numeric check for salary
            try {
                Double.parseDouble(salaryStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Salary must be a valid numeric value.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // Format message string
            String details = "=== Employee Registration Details ===\n\n"
                    + "Employee ID : " + empId + "\n"
                    + "Name        : " + name + "\n"
                    + "Department  : " + department + "\n"
                    + "Salary      : ₹" + salaryStr;

            // Display details in a Dialog Box
            JOptionPane.showMessageDialog(
                this,
                details,
                "Registration Details",
                JOptionPane.INFORMATION_MESSAGE
            );

        } else if (e.getSource() == btnClear) {
            tfEmpId.setText("");
            tfName.setText("");
            cbDepartment.setSelectedIndex(0);
            tfSalary.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EmployeeJavaSwing().setVisible(true);
        });
    }
}