import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StudentSwing extends JFrame implements ActionListener {

    // GUI Components
    private JTextField tfPrn, tfName, tfEmail;
    private JRadioButton rbMale, rbFemale;
    private ButtonGroup genderGroup;
    private JComboBox<String> cbBranch;
    private JButton btnSubmit, btnReset;
    private JTextArea taOutput;

    public StudentSwing() {
        // Frame Configuration
        setTitle("Student Registration Form");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout(10, 10));

        // 1. Header Label
        JLabel lblHeader = new JLabel("Student Registration", JLabel.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 18));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblHeader, BorderLayout.NORTH);

        // 2. Form Input Panel (GridBagLayout for neat alignment)
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // PRN
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("PRN:"), gbc);
        gbc.gridx = 1;
        tfPrn = new JTextField(15);
        formPanel.add(tfPrn, gbc);

        // Name
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        tfName = new JTextField(15);
        formPanel.add(tfName, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Email ID:"), gbc);
        gbc.gridx = 1;
        tfEmail = new JTextField(15);
        formPanel.add(tfEmail, gbc);

        // Gender (Radio Buttons)
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        formPanel.add(genderPanel, gbc);

        // Branch (Combo Box)
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        String[] branches = {"Select Department", "CSE", "AI/ML", "IT", "Mechanical", "Civil"};
        cbBranch = new JComboBox<>(branches);
        formPanel.add(cbBranch, gbc);

        // Buttons Panel
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnSubmit = new JButton("Register");
        btnReset = new JButton("Reset");
        btnSubmit.addActionListener(this);
        btnReset.addActionListener(this);
        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnReset);
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // 3. Output Display Area
        taOutput = new JTextArea(8, 30);
        taOutput.setEditable(false);
        taOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(taOutput);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Registration Details"));
        add(scrollPane, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            String prn = tfPrn.getText().trim();
            String name = tfName.getText().trim();
            String email = tfEmail.getText().trim();
            String gender = rbMale.isSelected() ? "Male" : (rbFemale.isSelected() ? "Female" : "Not Specified");
            String branch = (String) cbBranch.getSelectedItem();

            // Simple validation
            if (prn.isEmpty() || name.isEmpty() || email.isEmpty() || branch.equals("Select Department")) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Display submitted details
            StringBuilder sb = new StringBuilder();
            sb.append("=== Registration Successful ===\n");
            sb.append("PRN         : ").append(prn).append("\n");
            sb.append("Name        : ").append(name).append("\n");
            sb.append("Email       : ").append(email).append("\n");
            sb.append("Gender      : ").append(gender).append("\n");
            sb.append("Department  : ").append(branch).append("\n");

            taOutput.setText(sb.toString());

        } else if (e.getSource() == btnReset) {
            tfPrn.setText("");
            tfName.setText("");
            tfEmail.setText("");
            genderGroup.clearSelection();
            cbBranch.setSelectedIndex(0);
            taOutput.setText("");
        }
    }

    public static void main(String[] args) {
        // Run GUI construction on the Event Dispatch Thread (standard Swing practice)
        SwingUtilities.invokeLater(() -> {
            StudentSwing form = new StudentSwing();
            form.setVisible(true);
        });
    }
}