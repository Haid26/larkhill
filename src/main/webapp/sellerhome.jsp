<%@ page import="util.Answers" %>
<%@ page import="util.Product" %><%--
  Created by IntelliJ IDEA.
  User: Evgeniy Karpov
  Date: 12.10.2017
  Time: 5:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="views/style.css" type="text/css" />
    <title>Seller home</title>
</head>
<body>
<header></header><form action="RefreshProd" method="get">
    <input type="submit" value="refresh data">
</form>
<% Answers ans = (Answers)request.getSession().getAttribute("Answers");
    if ((ans.getFrom().endsWith("ogin")||ans.getFrom().endsWith("AddProdReq")))
    { %>
<b1> press refresh data to upload info about products </b1>
<%
} else {
    Product virus = (Product)request.getSession().getAttribute("Virus");
    Product cure = (Product) request.getSession().getAttribute("Cure");
%>
<form action ="UpdateProd" method="post">
    <table>
        <tr>
            <td>name</td>
            <td>available on stock</td>
            <td>total sold today</td>
        </tr>
        <tr>
            <td><%=virus.getName()%></td>
            <td><%=virus.getAmount()%></td>
            <td><input type="text" name="VirusAmount" value="0"></td>
        </tr>
        <tr>
            <td><%=cure.getName()%></td>
            <td><%=cure.getAmount()%></td>
            <td><input type="text" name="CureAmount" value="0"></td>
        </tr>
    </table>
    <input type="hidden" name="UserType" value="sell">
    <input type ="hidden" name="Virus_am" value="<%=virus.getAmount()%>">
    <input type ="hidden" name="Cure_am" value="<%=cure.getAmount()%>">
    <input type="submit" value="Update data">
</form>
<%}%>
<form action="AddProdReq" method="post">
    <Strong>Add request for laboratory</Strong><br>
    Product type<select name="ProdType">
        <option value="Virus">virus</option>
        <option value="Cure">cure</option>
    </select><br>
    Amount<input type="text" name="amount"><br>
    <input type="submit" value="add request"><br>
</form>
<form action="Logout" method="post">
    <input type="submit" value="Logout" >
</form>
</body>
</html>
