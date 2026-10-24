<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Patient Record</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 40px; background-color: #f0f4f8; }
        .card { max-width: 480px; margin: auto; background: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
        .form-group { margin-bottom: 16px; }
        label { display: block; margin-bottom: 6px; font-weight: 600; color: #2d3748; }
        input[type="text"], input[type="number"], select { width: 100%; padding: 10px; box-sizing: border-box; border: 1px solid #cbd5e0; border-radius: 4px; font-size: 14px; }
        .btn-save { background-color: #2b6cb0; color: white; padding: 10px; border: none; border-radius: 4px; cursor: pointer; width: 100%; font-weight: bold; font-size: 14px; }
        .btn-save:hover { background-color: #2c5282; }
        .cancel-link { display: block; text-align: center; margin-top: 14px; color: #718096; text-decoration: none; font-weight: 500; }
        .cancel-link:hover { text-decoration: underline; }
    </style>
</head>
<body>

<div class="card">
    <h2>Edit Patient Record</h2>

    <%
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendRedirect("index.jsp");
            return;
        }

        String dbUrl = "jdbc:mysql://localhost:3306/hospital_db?useSSL=false&serverTimezone=UTC";
        String dbUser = "root";
        String dbPass = "root";

        String name = "", disease = "", status = "";
        int age = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                 PreparedStatement ps = conn.prepareStatement("SELECT * FROM patients WHERE patient_id = ?")) {
                
                ps.setInt(1, Integer.parseInt(idStr));
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        name = rs.getString("name");
                        age = rs.getInt("age");
                        disease = rs.getString("disease");
                        status = rs.getString("treatment_status");
                    } else {
                        response.sendRedirect("index.jsp");
                        return;
                    }
                }
            }
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error retrieving record: " + e.getMessage() + "</p>");
        }
    %>

    <form action="patient_action.jsp" method="POST">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= idStr %>">

        <div class="form-group">
            <label>Patient ID</label>
            <input type="text" value="#<%= idStr %>" disabled style="background-color: #edf2f7;">
        </div>

        <div class="form-group">
            <label>Patient Full Name</label>
            <input type="text" name="name" value="<%= name %>" required>
        </div>

        <div class="form-group">
            <label>Age</label>
            <input type="number" name="age" min="1" max="130" value="<%= age %>" required>
        </div>

        <div class="form-group">
            <label>Diagnosed Disease</label>
            <input type="text" name="disease" value="<%= disease %>" required>
        </div>

        <div class="form-group">
            <label>Treatment Status</label>
            <select name="treatment_status" required>
                <option value="Under Treatment" <%= "Under Treatment".equals(status) ? "selected" : "" %>>Under Treatment</option>
                <option value="Recovered" <%= "Recovered".equals(status) ? "selected" : "" %>>Recovered</option>
                <option value="Discharged" <%= "Discharged".equals(status) ? "selected" : "" %>>Discharged</option>
                <option value="Critical" <%= "Critical".equals(status) ? "selected" : "" %>>Critical</option>
            </select>
        </div>

        <button type="submit" class="btn-save">Update Patient Details</button>
        <a href="index.jsp" class="cancel-link">Cancel</a>
    </form>
</div>

</body>
</html>