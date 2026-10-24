<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
    request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("action");

    String dbUrl = "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC";
    String dbUser = "root";
    String dbPass = "root"; // Update with your MySQL password

    if (action != null) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {

                // 1. INSERT (Create)
                if ("insert".equalsIgnoreCase(action)) {
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String course = request.getParameter("course");

                    String sql = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setString(1, name);
                        ps.setString(2, email);
                        ps.setString(3, course);
                        ps.executeUpdate();
                    }
                }

                // 2. UPDATE (Update)
                else if ("update".equalsIgnoreCase(action)) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String course = request.getParameter("course");

                    String sql = "UPDATE students SET name = ?, email = ?, course = ? WHERE id = ?";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setString(1, name);
                        ps.setString(2, email);
                        ps.setString(3, course);
                        ps.setInt(4, id);
                        ps.executeUpdate();
                    }
                }

                // 3. DELETE (Delete)
                else if ("delete".equalsIgnoreCase(action)) {
                    int id = Integer.parseInt(request.getParameter("id"));

                    String sql = "DELETE FROM students WHERE id = ?";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setInt(1, id);
                        ps.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            out.println("<h3>Operation failed: " + e.getMessage() + "</h3>");
            out.println("<a href='index.jsp'>Back to Main Page</a>");
            return;
        }
    }

    // Redirect to index page after completing the action
    response.sendRedirect("index.jsp");
%>