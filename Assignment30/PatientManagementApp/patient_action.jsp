<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
    request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("action");

    String dbUrl = "jdbc:mysql://localhost:3306/hospital_db?useSSL=false&serverTimezone=UTC";
    String dbUser = "root";
    String dbPass = "root"; // Update with your MySQL password

    if (action != null) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {

                // 1. CREATE
                if ("insert".equalsIgnoreCase(action)) {
                    String name = request.getParameter("name");
                    int age = Integer.parseInt(request.getParameter("age"));
                    String disease = request.getParameter("disease");
                    String status = request.getParameter("treatment_status");

                    String sql = "INSERT INTO patients (name, age, disease, treatment_status) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setString(1, name);
                        ps.setInt(2, age);
                        ps.setString(3, disease);
                        ps.setString(4, status);
                        ps.executeUpdate();
                    }
                }

                // 2. UPDATE
                else if ("update".equalsIgnoreCase(action)) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    String name = request.getParameter("name");
                    int age = Integer.parseInt(request.getParameter("age"));
                    String disease = request.getParameter("disease");
                    String status = request.getParameter("treatment_status");

                    String sql = "UPDATE patients SET name = ?, age = ?, disease = ?, treatment_status = ? WHERE patient_id = ?";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setString(1, name);
                        ps.setInt(2, age);
                        ps.setString(3, disease);
                        ps.setString(4, status);
                        ps.setInt(5, id);
                        ps.executeUpdate();
                    }
                }

                // 3. DELETE
                else if ("delete".equalsIgnoreCase(action)) {
                    int id = Integer.parseInt(request.getParameter("id"));

                    String sql = "DELETE FROM patients WHERE patient_id = ?";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setInt(1, id);
                        ps.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            out.println("<h3 style='color:red;'>Database Operation Error: " + e.getMessage() + "</h3>");
            out.println("<a href='index.jsp'>Back to Main Page</a>");
            return;
        }
    }

    response.sendRedirect("index.jsp");
%>