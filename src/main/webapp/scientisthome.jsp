<%@ page import="util.Answers" %>
<%@ page import="util.Product" %>
<%@ page import="util.Missions" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="util.ProdMissions" %><%--
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
    <title>Scientists</title>
</head>
<body>
<header></header>
<form action="RefreshProd" method="get">
    <input type="submit" value="refresh data">
</form>
    <% Answers ans = (Answers)request.getSession().getAttribute("Answers");
if (ans.getFrom().endsWith("ogin")||ans.getFrom().endsWith("UpdateProdReq")||ans.getFrom().endsWith("RefreshProdReq"))
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
        <td>produced today</td>
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
    <input type="hidden" name="UserType" value="sci">
    <input type ="hidden" name="Virus_am" value="<%=virus.getAmount()%>">
    <input type ="hidden" name="Cure_am" value="<%=cure.getAmount()%>">
    <input type="submit" value="Update data">
</form>
<%}%>

<form action="RefreshProdReq" method="get">
    <input type="submit" value="refresh list">
</form>
<%
    if (!(ans.getFrom().endsWith("RefreshProdReq")|| ans.getFrom().endsWith("UpdateProdReq")))
    { %>
<b1> press refresh list to upload list of missions </b1>
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
        <td><% if(mis.get(i).getStatus()==0) { %>
            <form action="UpdateProdReq" method="post">
                <select name="StatusReq" onchange="this.form.submit()">
                    <option selected value ="0">Created</option>
                    <option value = "1">In progress</option>
                    <option value = "2">Finished</option>
                </select>
                <input type="hidden" name="ProdMissionId" value="<%=mis.get(i).getId()%>">
            </form>
            <% } else
            if (mis.get(i).getId()==1){%>
            <form action="UpdateProdReq" method="post">
                <select name="StatusReq" onchange="this.form.submit()">
                    <option  value ="0">Created</option>
                    <option selected value = "1">In progress</option>
                    <option value = "2">Finished</option>
                </select>
                <input type="hidden" name="ProdMissionId" value="<%=mis.get(i).getId()%>">
            </form>
            <%} else {%>
            <form action="UpdateProdReq" method="post">
                <select name="StatusReq" onchange="this.form.submit()">
                    <option value ="0">Created</option>
                    <option selected value = "1">In progress</option>
                    <option value = "2">Finished</option>
                </select>
                <input type="hidden" name="ProdMissionId" value="<%=mis.get(i).getId()%>">
            </form>
            <% } %>

        </td>

    </tr>
    <%} %>
</table>

<% }
}%>

<form action="Logout" method="post">
    <input type="submit" value="Logout" >
</form>
</body>
</html>
