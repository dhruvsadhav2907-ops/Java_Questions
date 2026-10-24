<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Management System</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background-color: #f4f6f9; }
        .container { max-width: 950px; margin: auto; background: #fff; padding: 25px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.08); }
        h2, h3 { color: #2c3e50; }
        .form-row { display: flex; gap: 12px; margin-top: 15px; flex-wrap: wrap; }
        .form-row input { padding: 9px; border: 1px solid #ccc; border-radius: 4px; flex: 1; min-width: 160px; font-size: 14px; }
        .btn { padding: 9px 16px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; font-size: 13px; font-weight: bold; }
        .btn-add { background-color: #28a745; color: white; }
        .btn-edit { background-color: #ffc107; color: #212529; }
        .btn-delete { background-color: #dc3545; color: white; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #e1e4e8; padding: 12px; text-align: left; }
        th { background-color: #007bff; color: white; font-weight: 600; }
        tr:nth-child(even) { background-color: #f8f9fa; }
        .actions-cell { white-space: nowrap; }
    </style>
</head>
<body>

<div class="container">
    <h2>Employee Management System</h2>

    <!-- ADD EMPLOYEE FORM (CREATE) -->
    <h3>Add New Employee</h3>
    <form action="employee_action.jsp" method="POST">
        <input type="hidden" name="action" value="insert">
        <div class="form-row">
            <input type="text" name="name" placeholder="Full Name" required>
            <input type="email" name="email" placeholder="Email Address" required>
            <input type="text" name="designation" placeholder="Designation" required>
            <input type="number" step="0.01" name="salary" placeholder="Salary" required>
            <button type="submit" class="btn btn-add">+ Add Employee</button>
        </div>
    </form>

    <hr style="margin: 30px 0; border: 0; border-top: 1px solid #e5e5e5;">

    <!-- VIEW EMPLOYEES (READ) -->
    <h3>Employee Directory</h3>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Designation</th>
                <th>Salary</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <%
            String dbUrl = "jdbc:mysql://localhost:3306/company_db?useSSL=false&serverTimezone=UTC";
            String dbUser = "root";
            String dbPass = "root"; // Update with your MySQL password

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT * FROM employees ORDER BY emp_id ASC")) {

                    boolean hasData = false;
                    while (rs.next()) {
                        hasData = true;
                        int id = rs.getInt("emp_id");
                        String name = rs.getString("name");
                        String email = rs.getString("email");
                        String designation = rs.getString("designation");
                        double salary = rs.getDouble("salary");
        %>
            <tr>
                <td><%= id %></td>
                <td><%= name %></td>
                <td><%= email %></td>
                <td><%= designation %></td>
                <td><%= String.format("%.2f", salary) %></td>
                <td class="actions-cell">
                    <a href="edit_employee.jsp?id=<%= id %>" class="btn btn-edit">Edit</a>
                    <a href="employee_action.jsp?action=delete&id=<%= id %>" 
                       class="btn btn-delete" 
                       onclick="return confirm('Are you sure you want to delete employee ID <%= id %>?');">Delete</a>
                </td>
            </tr>
        <%
                    }
                    if (!hasData) {
        %>
            <tr>
                <td colspan="6" style="text-align: center; color: #888;">No employee records found.</td>
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