<%@ page import="util.Answers" %>
<%@ page import="util.Product" %>
<%@ page import="util.ProdMissions" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="util.Missions" %><%--
  Created by IntelliJ IDEA.
  User: Evgeniy Karpov
  Date: 12.10.2017
  Time: 5:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="views/style.css" type="text/css" />
    <title>Government home</title>
</head>
<body>
<header></header>
<br><strong>Product info</strong><br>
<form action="RefreshProd" method="get">
    <input type="submit" value="refresh data">
</form>
<% Answers ans = (Answers)request.getSession().getAttribute("Answers");
    if (!(ans.getFrom().endsWith("RefreshProd")))
    { %>
<b1> press refresh data to upload info about products </b1>
<%
} else {
    Product virus = (Product)request.getSession().getAttribute("Virus");
    Product cure = (Product) request.getSession().getAttribute("Cure");
%>
    <table>
        <tr>
            <td>name</td>
            <td>available on stock</td>
            <td>total sold</td>
        </tr>
        <tr>
            <td><%=virus.getName()%></td>
            <td><%=virus.getAmount()%></td>
            <td><%=virus.getSold()%></td>

        </tr>
        <tr>
            <td><%=cure.getName()%></td>
            <td><%=cure.getAmount()%></td>
            <td><%=cure.getSold()%></td>
        </tr>
    </table>
<% } %>
<br><strong> Products requests list</strong><br>
<form action="RefreshProdReq" method="get">
    <input type="submit" value="refresh list">
</form>
<%
    if (!(ans.getFrom().endsWith("RefreshProdReq")||ans.getFrom().endsWith("RefreshMission")||ans.getFrom().endsWith("AddMission")))
    { %>
<b1> press refresh list to upload list of requests </b1>
<%
} else {
    List<ProdMissions> mis = (ArrayList)request.getSession().getAttribute("ProdMissions");
    if(mis.isEmpty()) {
%>
<b1> Mission list is empty, refresh mission list by pressing refresh list</b1>
<%} else { %>

<table>

    <tr>
        <td>ID</td>
        <td>Product name</td>
        <td>amount</td>
        <td>status</td>
    </tr>
    <%
        for (int i = 0; i< mis.size();i++) {
            //System.out.println(iterator.next());
    %>
    <tr>
        <td ><%=mis.get(i).getId()%></td>
        <td ><%=mis.get(i).getName()%></td>
        <td><%=mis.get(i).getAmount()%></td>
        <% if(mis.get(i).getStatus()==0) { %>
        <td>created<td>
            <% } else
            if (mis.get(i).getStatus()==1){%>
        <td>in progress</td>
            <%} else {%>
        <td>finished</td>

            <% } %>

    </tr>
    <%} %>
</table>

<% }
}%>
<br><strong> missions list</strong><br>
<form action="RefreshMission" method="get">
    <input type="submit" value="refresh list">
</form>
<%
    if (!(ans.getFrom().endsWith("RefreshMission")|| ans.getFrom().endsWith("AddMission")))
    { %>
<b1> press refresh list to upload list of missions </b1>
<%
} else {
    List<Missions> mis = (ArrayList)request.getSession().getAttribute("Missions");
    if(mis.isEmpty()) {
%>
<b1> Mission list is empty</b1>
<%} else { %>

<table>
    <!--:forEach var="mission" items="${mis.Missions}"-->
    <tr>
        <td>ID</td>
        <td>Descritpion</td>
        <td>status</td>
    </tr>
    <%
        for (int i = 0; i< mis.size();i++) {
            //System.out.println(iterator.next());
    %>
    <tr>
        <td ><%=mis.get(i).getId()%></td>
        <td ><%=mis.get(i).getDesc()%></td>
        <% if(mis.get(i).getStatus()==0) { %>
        <td>created<td>
            <%} else
            if (mis.get(i).getStatus()==1){%>
        <td>in progress</td>
        <%} else {%>
        <td>finished</td>
        <% } %>

    </tr>
    <%} %>
</table>
<% }
}%>
<br><strong> add mission</strong><br>
<form action="AddMission" method="post">
    Description mission<input type="text" name="desc"><br>
    <input type="submit" value="add mission"><br>
</form>
<br><strong> get info about</strong><br>
<form action ="ResultFeedback" method="post">
    <input type="submit" value="get info">
</form>
<% if(ans.getFrom().endsWith("ResultFeedback"))
{
    int anss=(Integer)request.getSession().getAttribute("ResFed");%>
<br><b1>people are <%if (anss<0) {%> un<%}%>happy</b1>
<%}%>
<form action="Logout" method="post">
    <input type="submit" value="Logout" >
</form>
</body>
</html>
