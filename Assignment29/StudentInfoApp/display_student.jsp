<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Submitted Student Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 40px;
        }
        .container {
            max-width: 550px;
            margin: auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
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
            width: 40%;
            color: #495057;
        }
        td {
            color: #212529;
            font-weight: 500;
        }
        .action-link {
            display: block;
            text-align: center;
            margin-top: 25px;
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }
        .action-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Student Details Summary</h2>

    <%
        // Set request character encoding to handle special characters properly
        request.setCharacterEncoding("UTF-8");

        // Extract student details via the 'request' implicit object
        String prn = request.getParameter("prn");
        String fullName = request.getParameter("fullName");
        String branch = request.getParameter("branch");
        String semester = request.getParameter("semester");
        String email = request.getParameter("email");

        // Format current timestamp for submission record
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        String submissionTime = sdf.format(new Date());
    %>

    <table>
        <tr>
            <th>PRN / Roll No:</th>
            <td><%= (prn != null && !prn.trim().isEmpty()) ? prn : "N/A" %></td>
        </tr>
        <tr>
            <th>Student Name:</th>
            <td><%= (fullName != null && !fullName.trim().isEmpty()) ? fullName : "N/A" %></td>
        </tr>
        <tr>
            <th>Branch / Dept:</th>
            <td><%= (branch != null) ? branch : "N/A" %></td>
        </tr>
        <tr>
            <th>Semester:</th>
            <td>Semester <%= (semester != null) ? semester : "N/A" %></td>
        </tr>
        <tr>
            <th>Email Address:</th>
            <td><%= (email != null && !email.trim().isEmpty()) ? email : "N/A" %></td>
        </tr>
        <tr>
            <th>Recorded At:</th>
            <td><%= submissionTime %></td>
        </tr>
    </table>

    <a href="student_form.jsp" class="action-link">&larr; Register Another Student</a>
</div>

</body>
</html>