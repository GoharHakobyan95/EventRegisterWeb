<%@ page import="java.util.List" %>
<%@ page import="model.Event" %>
<%@ page import="model.User" %><%--
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
    User user = (User) session.getAttribute("user");
    String keyword = (String) request.getAttribute("keyword");
%>
<form action="/events" method="get">
    <input type="text" name="keyword" value="<%=keyword%>">
    Min Price: <input type="number" name="minPrice"/>
    Max Price: <input type="number" name="maxPrice"/>

    <input type="submit" value="search">
</form>

<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>place</th>
        <th>idsOnline</th>
        <th>price</th>
        <th>event type</th>
        <th>event date</th>
        <th>action</th>
        <th>join</th>
            <% for (Event event : events) { %>
    <tr>
        <td><%=event.getId()%>
        </td>
        <td><%=event.getName()%>
        </td>
        <td><%=event.getPlace()%>
        </td>
        <td><%=event.isOnline()%>
        </td>
        <td><%=event.getPrice()%>
        </td>
        <td><%=event.getType().name()%>
        </td>
        <td><%=event.getEventDate()%>
        </td>
        <td>
            <a href="/events/remove?eventId=<%=event.getId()%>">Remove</a>
        </td>
        <td>
            <% if (event.getUsers().contains(user)) { %>
            <a href="/events/cancel?eventId=<%=event.getId()%>">Cancel</a> |

            <%} else {%>
            <a href="/events/join?eventId=<%=event.getId()%>">Join</a> |

            <a href="/events/edit?eventId=<%=event.getId()%>">Edit</a>
            <%} %>
        </td>
    </tr>
    <% }
    %>
    </tr>
</table>
</body>
</html>
