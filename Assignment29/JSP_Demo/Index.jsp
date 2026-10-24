<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP Fundamentals Demo</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; line-height: 1.6; }
        .card { border: 1px solid #ddd; padding: 15px; margin-bottom: 20px; border-radius: 6px; }
        .highlight { color: #0066cc; font-weight: bold; }
        label { display: block; margin-top: 8px; font-weight: bold; }
        input[type="text"], input[type="number"] { width: 250px; padding: 6px; margin-top: 4px; }
        input[type="submit"] { margin-top: 12px; padding: 8px 16px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }
    </style>
</head>
<body>

    <h2>JSP Core Elements Demonstration</h2>

    <%-- 1. DECLARATION TAG: Methods and instance variables --%>
    <%! 
        private int visitCounter = 0; 

        public String getGreetingMessage(String name) {
            return "Welcome, " + (name == null || name.isEmpty() ? "Guest" : name) + "!";
        }
    %>

    <%-- 2. SCRIPTLET TAG: Java statements executed per request --%>
    <%
        visitCounter++;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new Date());
    %>

    <div class="card">
        <h3>1. Directives, Declarations, and Expressions</h3>
        <p><strong>Server Time:</strong> <span class="highlight"><%= currentTime %></span> (via Expression Tag)</p>
        <p><strong>Page Visit Counter:</strong> <span class="highlight"><%= visitCounter %></span> (via Declaration variable)</p>
        <p><strong>Greeting Method Output:</strong> <%= getGreetingMessage("Student") %></p>
    </div>

    <div class="card">
        <h3>2. Form Handling & Request Parameter Transfer</h3>
        <form action="process.jsp" method="POST">
            <label for="username">Name:</label>
            <input type="text" id="username" name="username" placeholder="Enter your name" required>

            <label for="score">Score (0-100):</label>
            <input type="number" id="score" name="score" min="0" max="100" placeholder="Enter score" required>

            <label for="iterations">Number of Repeats:</label>
            <input type="number" id="iterations" name="iterations" min="1" max="10" value="3" required>

            <br>
            <input type="submit" value="Submit to Process.jsp">
        </form>
    </div>

</body>
</html>