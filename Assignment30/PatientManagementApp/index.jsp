<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hospital Patient Management</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 30px; background-color: #f0f4f8; }
        .container { max-width: 1000px; margin: auto; background: #ffffff; padding: 25px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
        h2, h3 { color: #1a365d; margin-top: 0; }
        .form-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 12px; margin-bottom: 12px; }
        .form-grid input, .form-grid select { padding: 9px; border: 1px solid #cbd5e0; border-radius: 5px; font-size: 14px; width: 100%; box-sizing: border-box; }
        .btn { padding: 9px 16px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; font-size: 13px; font-weight: 600; display: inline-block; }
        .btn-add { background-color: #2b6cb0; color: white; width: 100%; }
        .btn-add:hover { background-color: #2c5282; }
        .btn-edit { background-color: #d69e2e; color: white; }
        .btn-delete { background-color: #e53e3e; color: white; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #e2e8f0; padding: 12px; text-align: left; }
        th { background-color: #2b6cb0; color: white; font-weight: 600; }
        tr:nth-child(even) { background-color: #f7fafc; }
        .badge { padding: 4px 8px; border-radius: 12px; font-size: 12px; font-weight: bold; }
        .status-under-treatment { background-color: #feebc8; color: #c05621; }
        .status-recovered { background-color: #c6f6d5; color: #22543d; }
        .status-discharged { background-color: #e2e8f0; color: #4a5568; }
        .status-critical { background-color: #fed7d7; color: #9b2c2c; }
    </style>
</head>
<body>

<div class="container">
    <h2>Hospital Patient Record System</h2>

    <!-- CREATE FORM -->
    <h3>Register New Patient</h3>
    <form action="patient_action.jsp" method="POST">
        <input type="hidden" name="action" value="insert">
        <div class="form-grid">
            <input type="text" name="name" placeholder="Patient Full Name" required>
            <input type="number" name="age" min="1" max="130" placeholder="Age" required>
            <input type="text" name="disease" placeholder="Diagnosed Disease/Condition" required>
            <select name="treatment_status" required>
                <option value="" disabled selected>Select Status</option>
                <option value="Under Treatment">Under Treatment</option>
                <option value="Recovered">Recovered</option>
                <option value="Discharged">Discharged</option>
                <option value="Critical">Critical</option>
            </select>
        </div>
        <button type="submit" class="btn btn-add">+ Add Patient Record</button>
    </form>

    <hr style="margin: 30px 0; border: 0; border-top: 1px solid #e2e8f0;">

    <!-- READ PATIENT TABLE -->
    <h3>Current In-Patient & Out-Patient Records</h3>
    <table>
        <thead>
            <tr>
                <th>Patient ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Disease</th>
                <th>Treatment Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <%
            String dbUrl = "jdbc:mysql://localhost:3306/hospital_db?useSSL=false&serverTimezone=UTC";
            String dbUser = "root";
            String dbPass = "root"; // Update with your MySQL password

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT * FROM patients ORDER BY patient_id ASC")) {

                    boolean hasRecords = false;
                    while (rs.next()) {
                        hasRecords = true;
                        int id = rs.getInt("patient_id");
                        String name = rs.getString("name");
                        int age = rs.getInt("age");
                        String disease = rs.getString("disease");
                        String status = rs.getString("treatment_status");

                        String badgeClass = "status-" + status.toLowerCase().replace(" ", "-");
        %>
            <tr>
                <td>#<%= id %></td>
                <td><%= name %></td>
                <td><%= age %></td>
                <td><%= disease %></td>
                <td><span class="badge <%= badgeClass %>"><%= status %></span></td>
                <td>
                    <a href="edit_patient.jsp?id=<%= id %>" class="btn btn-edit">Edit</a>
                    <a href="patient_action.jsp?action=delete&id=<%= id %>" 
                       class="btn btn-delete" 
                       onclick="return confirm('Are you sure you want to delete patient record #<%= id %>?');">Delete</a>
                </td>
            </tr>
        <%
                    }
                    if (!hasRecords) {
        %>
            <tr>
                <td colspan="6" style="text-align: center; color: #718096;">No patient records available.</td>
            </tr>
        <%
                    }
                }
            } catch (Exception e) {
                out.println("<tr><td colspan='6' style='color:red;'>Database Connection Error: " + e.getMessage() + "</td></tr>");
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>