import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Bank_buttonhandling extends JFrame implements ActionListener {

    // GUI Components
    private JTextField tfInitialBalance, tfAmount, tfCurrentBalance;
    private JButton btnDeposit, btnWithdraw, btnReset;

    public Bank_buttonhandling() {
        // Frame Settings
        setTitle("Bank Balance Calculator");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout(10, 10));

        // 1. Header Title
        JLabel lblTitle = new JLabel("Bank Account Balance Manager", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        add(lblTitle, BorderLayout.NORTH);

        // 2. Center Form Panel (GridLayout)
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 10, 25));

        formPanel.add(new JLabel("Initial / Balance (₹):"));
        tfInitialBalance = new JTextField();
        formPanel.add(tfInitialBalance);

        formPanel.add(new JLabel("Transaction Amount (₹):"));
        tfAmount = new JTextField();
        formPanel.add(tfAmount);

        formPanel.add(new JLabel("Updated Balance (₹):"));
        tfCurrentBalance = new JTextField();
        tfCurrentBalance.setEditable(false); // Read-only field
        tfCurrentBalance.setFont(new Font("Arial", Font.BOLD, 13));
        tfCurrentBalance.setBackground(new Color(235, 235, 235));
        formPanel.add(tfCurrentBalance);

        add(formPanel, BorderLayout.CENTER);

        // 3. Bottom Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        btnDeposit = new JButton("Deposit (+)");
        btnWithdraw = new JButton("Withdraw (-)");
        btnReset = new JButton("Reset");

        btnDeposit.addActionListener(this);
        btnWithdraw.addActionListener(this);
        btnReset.addActionListener(this);

        buttonPanel.add(btnDeposit);
        buttonPanel.add(btnWithdraw);
        buttonPanel.add(btnReset);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle Reset Action
        if (e.getSource() == btnReset) {
            tfInitialBalance.setText("");
            tfAmount.setText("");
            tfCurrentBalance.setText("");
            tfInitialBalance.setEditable(true);
            return;
        }

        String balanceStr = tfInitialBalance.getText().trim();
        String amountStr = tfAmount.getText().trim();

        // Validation for empty inputs
        if (balanceStr.isEmpty() || amountStr.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter both Initial Balance and Transaction Amount.",
                "Missing Input",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            double currentBalance = Double.parseDouble(balanceStr);
            double amount = Double.parseDouble(amountStr);

            // Validation: Negative inputs
            if (currentBalance < 0) {
                JOptionPane.showMessageDialog(this, "Initial balance cannot be negative.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Transaction amount must be greater than zero.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Perform Transaction Logic
            if (e.getSource() == btnDeposit) {
                currentBalance += amount;
                JOptionPane.showMessageDialog(this, "₹" + amount + " deposited successfully!", "Transaction Successful", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == btnWithdraw) {
                if (amount > currentBalance) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Insufficient Funds! Transaction declined.\nCurrent Balance: ₹" + String.format("%.2f", currentBalance),
                        "Transaction Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                currentBalance -= amount;
                JOptionPane.showMessageDialog(this, "₹" + amount + " withdrawn successfully!", "Transaction Successful", JOptionPane.INFORMATION_MESSAGE);
            }

            // Update UI with newly computed balance
            tfCurrentBalance.setText(String.format("%.2f", currentBalance));

            // Sync updated balance back to input to chain successive transactions
            tfInitialBalance.setText(String.format("%.2f", currentBalance));
            tfAmount.setText(""); // clear amount field for next transaction

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Invalid input format! Please enter valid numeric values only.",
                "Input Format Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bank_buttonhandling().setVisible(true);
        });
    }
}