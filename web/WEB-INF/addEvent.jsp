<%--
  Created by IntelliJ IDEA.
  User: Noname
  Date: 9/1/2022
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add event</title>
</head>
<body>
Please input event's data!
<form action="/events/add" method="post">
    <input type="text" name="name" placeholder="Please input name"><br>
    <input type="text" name="place" placeholder="Please input place"><br>
    Is event online?<br>
    Yes<input type="radio" name="isOnline" value="TRUE">
    No<input type="radio" name="isOnline" value="FALSE">
    <br>
    Please choose event's type.<br>
    <select name="eventType">
        <option value="FILM">FILM</option>
        <option value="SPORT">SPORT</option>
        <option value="GAME">GAME</option>
        <option value="WEBINAR">WEBINAR</option>
    </select>
    <br>
    <input type="number" name="price" placeholder="Please input price"><br>
    <input type="date" name="eventDate" placeholder="Please input date"><br>
    <input type="submit" value="Add">
</form>
</body>
</html>
