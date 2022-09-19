<%@ page import="model.User" %>
<%@ page import="model.UserRole" %><%--
  Created by IntelliJ IDEA.
  User: Noname
  Date: 9/1/2022
  Time: 12:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Event Manager</title>
</head>
<%
    User user = (User) session.getAttribute("user");
%>
<body>
<div style="width: 1000px; margin: 0 auto">
    <div>
        <img src="/images/eventDefaultPic.jpg" width="1000" height="400"/>
    </div>
    <div>
        <center>
        <br>
        <br>
        <a href="/events">Show All Events</a><br>
        <%
            if (user != null && user.getUserRole() == UserRole.ADMIN) {
        %>
        <a href="/events/add">Add Event</a>
        <%}%>

        <%
            if (user != null) {
        %>
        <a href="/users">Show all users</a><br>
        <a href="/logout">Logout</a><br>
        <%} else {%>
        <a href="/users/add">Register </a><br>

        <a href="/login">Login</a>
        <%}%>
        </center>
    </div>
</div>

</body>
</html>
