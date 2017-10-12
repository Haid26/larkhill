<%@page import="util.User"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Home Page</title>
</head>
<body>
<%User user = (User) session.getAttribute("User"); %>
<h3>Hi <%=user.getName() %></h3>
<strong>Your Login</strong>: <%=user.getLogin() %><br>
<strong>Your Role</strong>: <%=user.getRole() %><br>
<% if (user.getRole()== "solder")  {%>
<br> solder part<a><%} else { %>
<br> another role part<a>
       <% }  %>
<!--
<form action="MissionDownloadList" method="get">
    <input type="submit" value="MissionDownloadList" >
</form>
<%
            List mis = (ArrayList)request.getSession().getAttribute("Missions");
%>
<table>
    <c:forEach var="person" items="${people.people}">
        <tr>
            <td>${person.name}</td>
            <td>${person.age}</td>
            <td>${person.height}</td>
        </tr>
    </c:forEach>
</table>
<form action="MissionUpdate" method="post">
    <strong>User login</strong>:<input type="text" name="LoginName"><br>
    <strong>Password</strong>:<input type="password" name="password"><br>
    <input type="submit" value="MissionUpdate">
</form>
-->
<form action="Logout" method="post">
    <input type="submit" value="Logout" >
</form>
</body>
</html>