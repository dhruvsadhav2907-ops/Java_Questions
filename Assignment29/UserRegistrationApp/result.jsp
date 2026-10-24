<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Confirmation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
            margin: 0;
            padding: 40px;
        }
        .result-container {
            max-width: 500px;
            margin: auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #28a745;
            margin-top: 0;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #dee2e6;
        }
        th {
            background-color: #f8f9fa;
            width: 35%;
            color: #495057;
        }
        td {
            color: #212529;
            font-weight: 500;
        }
        .btn-back {
            display: block;
            text-align: center;
            margin-top: 25px;
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }
        .btn-back:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="result-container">
    <h2>Registration Successful</h2>

    <%
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        // Mask password characters with bullets/asterisks
        String maskedPassword = "";
        if (password != null && !password.isEmpty()) {
            maskedPassword = "•".repeat(password.length());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
        String registeredAt = sdf.format(new Date());
    %>

    <table>
        <tr>
            <th>Full Name:</th>
            <td><%= (name != null && !name.trim().isEmpty()) ? name : "N/A" %></td>
        </tr>
        <tr>
            <th>Email:</th>
            <td><%= (email != null && !email.trim().isEmpty()) ? email : "N/A" %></td>
        </tr>
        <tr>
            <th>Phone:</th>
            <td><%= (phone != null && !phone.trim().isEmpty()) ? phone : "N/A" %></td>
        </tr>
        <tr>
            <th>Password:</th>
            <td><%= maskedPassword %> (hidden for security)</td>
        </tr>
        <tr>
            <th>Registered At:</th>
            <td><%= registeredAt %></td>
        </tr>
    </table>

    <a href="register.jsp" class="btn-back">&larr; Back to Registration</a>
</div>

</body>
</html>