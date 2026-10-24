<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Management - CRUD Demo</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background-color: #f7f9fc; }
        .container { max-width: 900px; margin: auto; background: #fff; padding: 25px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
        h2, h3 { color: #333; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #007bff; color: white; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .form-inline { display: flex; gap: 10px; margin-top: 15px; }
        .form-inline input { padding: 8px; border: 1px solid #ccc; border-radius: 4px; flex: 1; }
        .btn { padding: 8px 14px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; font-size: 13px; }
        .btn-add { background-color: #28a745; color: white; }
        .btn-edit { background-color: #ffc107; color: #212529; }
        .btn-delete { background-color: #dc3545; color: white; }
    </style>
</head>
<body>

<div class="container">
    <h2>Student Records Management</h2>

    <!-- CREATE FORM -->
    <h3>Add New Student</h3>
    <form class="form-inline" action="action.jsp" method="POST">
        <input type="hidden" name="action" value="insert">
        <input type="text" name="name" placeholder="Full Name" required>
        <input type="email" name="email" placeholder="Email Address" required>
        <input type="text" name="course" placeholder="Course / Department" required>
        <button type="submit" class="btn btn-add">Add Student</button>
    </form>

    <hr style="margin: 25px 0; border: 0; border-top: 1px solid #eee;">

    <!-- READ TABLE -->
    <h3>Registered Students</h3>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Course</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <%
            String dbUrl = "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC";
            String dbUser = "root";
            String dbPass = "root"; // Update with your MySQL password

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT * FROM students ORDER BY id ASC")) {

                    boolean hasRecords = false;
                    while (rs.next()) {
                        hasRecords = true;
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String email = rs.getString("email");
                        String course = rs.getString("course");
        %>
            <tr>
                <td><%= id %></td>
                <td><%= name %></td>
                <td><%= email %></td>
                <td><%= course %></td>
                <td>
                    <a href="edit.jsp?id=<%= id %>" class="btn btn-edit">Edit</a>
                    <a href="action.jsp?action=delete&id=<%= id %>" 
                       class="btn btn-delete" 
                       onclick="return confirm('Are you sure you want to delete this record?');">Delete</a>
                </td>
            </tr>
        <%
                    }
                    if (!hasRecords) {
        %>
            <tr>
                <td colspan="5" style="text-align: center; color: #777;">No records found.</td>
            </tr>
        <%
                    }
                }
            } catch (Exception e) {
                out.println("<tr><td colspan='5' style='color:red;'>Database Error: " + e.getMessage() + "</td></tr>");
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>