import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator_buttonhandling extends JFrame implements ActionListener {

    // GUI Components
    private JTextField tfNum1, tfNum2, tfResult;
    private JButton btnAdd, btnSub, btnClear;

    public Calculator_buttonhandling() {
        // Frame setup
        setTitle("Simple Calculator");
        setSize(360, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window
        setLayout(new BorderLayout(10, 10));

        // 1. Input Panel (Grid Layout)
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        inputPanel.add(new JLabel("First Number:"));
        tfNum1 = new JTextField();
        inputPanel.add(tfNum1);

        inputPanel.add(new JLabel("Second Number:"));
        tfNum2 = new JTextField();
        inputPanel.add(tfNum2);

        inputPanel.add(new JLabel("Result:"));
        tfResult = new JTextField();
        tfResult.setEditable(false); // Result field is read-only
        tfResult.setFont(new Font("Arial", Font.BOLD, 12));
        inputPanel.add(tfResult);

        add(inputPanel, BorderLayout.CENTER);

        // 2. Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        btnAdd = new JButton("Add (+)");
        btnSub = new JButton("Subtract (-)");
        btnClear = new JButton("Clear");

        // Register ActionListeners
        btnAdd.addActionListener(this);
        btnSub.addActionListener(this);
        btnClear.addActionListener(this);

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnSub);
        buttonPanel.add(btnClear);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClear) {
            tfNum1.setText("");
            tfNum2.setText("");
            tfResult.setText("");
            return;
        }

        String input1 = tfNum1.getText().trim();
        String input2 = tfNum2.getText().trim();

        // Check for empty fields
        if (input1.isEmpty() || input2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both numbers.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Parse numbers and calculate
        try {
            double n1 = Double.parseDouble(input1);
            double n2 = Double.parseDouble(input2);
            double result = 0;

            if (e.getSource() == btnAdd) {
                result = n1 + n2;
            } else if (e.getSource() == btnSub) {
                result = n1 - n2;
            }

            // Display formatted result
            if (result == (long) result) {
                tfResult.setText(String.format("%d", (long) result));
            } else {
                tfResult.setText(String.format("%.4f", result));
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format. Please enter numeric values only.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculator_buttonhandling().setVisible(true);
        });
    }
}