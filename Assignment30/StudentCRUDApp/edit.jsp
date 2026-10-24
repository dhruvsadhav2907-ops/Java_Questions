<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Student Record</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f7f9fc; }
        .container { max-width: 450px; margin: auto; background: #fff; padding: 25px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
        .form-group { margin-bottom: 14px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="email"] { width: 100%; padding: 8px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; }
        .btn-update { background-color: #007bff; color: white; padding: 10px 16px; border: none; border-radius: 4px; cursor: pointer; width: 100%; font-weight: bold; }
        .back-link { display: block; text-align: center; margin-top: 15px; color: #555; text-decoration: none; }
    </style>
</head>
<body>

<div class="container">
    <h2>Edit Student</h2>
    <%
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect("index.jsp");
            return;
        }

        String dbUrl = "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC";
        String dbUser = "root";
        String dbPass = "root";

        String name = "", email = "", course = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                 PreparedStatement ps = conn.prepareStatement("SELECT * FROM students WHERE id = ?")) {
                ps.setInt(1, Integer.parseInt(idStr));
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        name = rs.getString("name");
                        email = rs.getString("email");
                        course = rs.getString("course");
                    } else {
                        response.sendRedirect("index.jsp");
                        return;
                    }
                }
            }
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error fetching record: " + e.getMessage() + "</p>");
        }
    %>

    <form action="action.jsp" method="POST">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= idStr %>">

        <div class="form-group">
            <label>Name</label>
            <input type="text" name="name" value="<%= name %>" required>
        </div>
        <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" value="<%= email %>" required>
        </div>
        <div class="form-group">
            <label>Course</label>
            <input type="text" name="course" value="<%= course %>" required>
        </div>

        <button type="submit" class="btn-update">Save Changes</button>
        <a href="index.jsp" class="back-link">Cancel</a>
    </form>
</div>

</body>
</html>