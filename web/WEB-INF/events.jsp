<%@ page import="java.util.List" %>
<%@ page import="model.Event" %><%--
  Created by IntelliJ IDEA.
  User: Noname
  Date: 9/1/2022
  Time: 9:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Events page</title>
</head>
<body>
<%
    List<Event> events = (List<Event>) request.getAttribute("events");
%>
<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>place</th>
        <th>idsOnline</th>
        <th>price</th>
        <th>event type</th>
        <th>event date</th>
            <% for (Event event : events) { %>
    <tr>
    <td><%=event.getId()%></td>
    <td><%=event.getName()%></td>
    <td><%=event.getPlace()%></td>
    <td><%=event.isOnline()%></td>
    <td><%=event.getPrice()%></td>
    <td><%=event.getType().name()%></td>
    <td><%=event.getEventDate()%></td>
</tr>
    <% }
    %>
    </tr>
</table>
</body>
</html>
