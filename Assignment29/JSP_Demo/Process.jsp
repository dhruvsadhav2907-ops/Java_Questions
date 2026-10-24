<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP Processing & Implicit Objects</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; line-height: 1.6; }
        .card { border: 1px solid #ddd; padding: 15px; margin-bottom: 20px; border-radius: 6px; }
        .pass { color: green; font-weight: bold; }
        .fail { color: red; font-weight: bold; }
    </style>
</head>
<body>

    <h2>JSP Processing Results</h2>

    <%
        // Extract form parameters using the 'request' implicit object
        String username = request.getParameter("username");
        String scoreStr = request.getParameter("score");
        String iterStr = request.getParameter("iterations");

        int score = 0;
        int iterations = 1;

        try {
            if (scoreStr != null) score = Integer.parseInt(scoreStr);
            if (iterStr != null) iterations = Integer.parseInt(iterStr);
        } catch (NumberFormatException e) {
            out.println("<p style='color:red;'>Invalid numerical format received.</p>");
        }

        // Store user into the 'session' implicit object
        session.setAttribute("currentUser", username);

        // Update application-wide visitor counter using 'application' implicit object
        Integer totalSubmissions = (Integer) application.getAttribute("totalSubmissions");
        if (totalSubmissions == null) {
            totalSubmissions = 1;
        } else {
            totalSubmissions++;
        }
        application.setAttribute("totalSubmissions", totalSubmissions);
    %>

    <div class="card">
        <h3>1. Request Data & Conditional Logic (if-else)</h3>
        <p><strong>Processed User:</strong> <%= username %></p>
        <p><strong>Score:</strong> <%= score %></p>
        <p><strong>Evaluation:</strong> 
            <% if (score >= 50) { %>
                <span class="pass">Passed</span>
            <% } else { %>
                <span class="fail">Failed</span>
            <% } %>
        </p>
    </div>

    <div class="card">
        <h3>2. Loop Execution (for-loop)</h3>
        <p>Printing message <%= iterations %> time(s):</p>
        <ul>
            <% for (int i = 1; i <= iterations; i++) { %>
                <li>Iteration <%= i %>: Hello, <%= username %>!</li>
            <% } %>
        </ul>
    </div>

    <div class="card">
        <h3>3. Implicit Objects Scope Data</h3>
        <p><strong>Session ID:</strong> <%= session.getId() %></p>
        <p><strong>Session User Attribute:</strong> <%= session.getAttribute("currentUser") %></p>
        <p><strong>Application Total Submissions:</strong> <%= application.getAttribute("totalSubmissions") %></p>
        <p><strong>Client Remote IP:</strong> <%= request.getRemoteAddr() %></p>
    </div>

    <p><a href="index.jsp">&larr; Back to Form</a></p>

</body>
</html>