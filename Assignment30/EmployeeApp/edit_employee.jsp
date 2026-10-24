<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Employee</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f6f9; }
        .card { max-width: 450px; margin: auto; background: #fff; padding: 25px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.08); }
        .form-group { margin-bottom: 14px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #333; }
        input[type="text"], input[type="email"], input[type="number"] { width: 100%; padding: 8px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; }
        .btn-save { background-color: #007bff; color: white; padding: 10px; border: none; border-radius: 4px; cursor: pointer; width: 100%; font-weight: bold; font-size: 14px; }
        .btn-cancel { display: block; text-align: center; margin-top: 14px; color: #6c757d; text-decoration: none; }
    </style>
</head>
<body>

<div class="card">
    <h2>Edit Employee</h2>

    <%
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendRedirect("index.jsp");
            return;
        }

        String dbUrl = "jdbc:mysql://localhost:3306/company_db?useSSL=false&serverTimezone=UTC";
        String dbUser = "root";
        String dbPass = "root";

        String name = "", email = "", designation = "";
        double salary = 0.0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                 PreparedStatement ps = conn.prepareStatement("SELECT * FROM employees WHERE emp_id = ?")) {
                
                ps.setInt(1, Integer.parseInt(idStr));
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        name = rs.getString("name");
                        email = rs.getString("email");
                        designation = rs.getString("designation");
                        salary = rs.getDouble("salary");
                    } else {
                        response.sendRedirect("index.jsp");
                        return;
                    }
                }
            }
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error retrieving employee: " + e.getMessage() + "</p>");
        }
    %>

    <form action="employee_action.jsp" method="POST">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= idStr %>">

        <div class="form-group">
            <label>Full Name</label>
            <input type="text" name="name" value="<%= name %>" required>
        </div>

        <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" value="<%= email %>" required>
        </div>

        <div class="form-group">
            <label>Designation</label>
            <input type="text" name="designation" value="<%= designation %>" required>
        </div>

        <div class="form-group">
            <label>Salary</label>
            <input type="number" step="0.01" name="salary" value="<%= salary %>" required>
        </div>

        <button type="submit" class="btn-save">Update Employee</button>
        <a href="index.jsp" class="btn-cancel">Cancel</a>
    </form>
</div>

</body>
</html>