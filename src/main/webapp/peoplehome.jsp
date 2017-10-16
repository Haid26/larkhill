<%@ page import="util.Answers" %><%--
  Created by IntelliJ IDEA.
  User: Evgeniy Karpov
  Date: 12.10.2017
  Time: 5:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="views/style.css" type="text/css" />
    <title>People</title>
</head>
<body>
<header></header>
<% Answers answer = (Answers) request.getSession().getAttribute("Answers");
    if (answer.getAnwer()){%>
<b1><font color=green>Feedback Succesfully send<br></font></b1>
<% } else {%>
<b1> Are you happy</b1>
<form action="SendFeedback" method="post">
    <p><input name="ask" type="radio" value="yes" checked> Yes</p>
    <p><input name="ask" type="radio" value="no"> NO</p>
    <input type="submit" value="Send">
</form>
<% }%>
<form action="Logout" method="post">
    <input type="submit" value="Logout" >
</form>
</body>
</html>
