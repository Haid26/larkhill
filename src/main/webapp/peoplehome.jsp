<%--
  Created by IntelliJ IDEA.
  User: Evgeniy Karpov
  Date: 12.10.2017
  Time: 5:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>People</title>
</head>
<body>
<b1> Are you happy</b1>
<form action="SendFeedback" method="post">
    <p><input name="ask" type="radio" value="yes"> Yes</p>
    <p><input name="ask" type="radio" value="no"> NO</p>
    <input type="submit" value="Send">
</form>
<form action="Logout" method="post">
    <input type="submit" value="Logout" >
</form>
</body>
</html>
