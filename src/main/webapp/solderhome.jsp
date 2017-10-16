<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="util.Missions" %>
<%@ page import="util.Answers" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: Evgeniy Karpov
  Date: 12.10.2017
  Time: 5:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="views/style.css" type="text/css" />
    <title>Solder home</title>
</head>
<body>
<header></header>
<form action="RefreshMission" method="get">
    <input type="submit" value="refresh list">
</form>
<% Answers ans = (Answers)request.getSession().getAttribute("Answers");
if (ans.getFrom().endsWith("ogin"))
{ %>
<b1> press refresh list to upload list of missions </b1>
<%
} else {
    List<Missions> mis = (ArrayList)request.getSession().getAttribute("Missions");
if(mis.isEmpty()) {
%>
<b1> Mission list is empty, refresh mission list by pressing refresh list</b1>
<%} else { %>

<table>
    <!--:forEach var="mission" items="${mis.Missions}"-->
       <%
            for (int i = 0; i< mis.size();i++) {
                //System.out.println(iterator.next());
            %>
        <tr>
            <td ><%=mis.get(i).getId()%></td>
            <td ><%=mis.get(i).getDesc()%></td>
            <td><% if(mis.get(i).getStatus()==0) { %>
                <form action="MissionUpdate" method="post">
                <select name="Status" onchange="this.form.submit()">
                    <option selected value ="0">Created</option>
                    <option value = "1">In progress</option>
                    <option value = "2">Finished</option>
                </select>
                    <input type="hidden" name="MissionId" value="<%=mis.get(i).getId()%>">
                </form>
                <% } else
                    if (mis.get(i).getId()==1){%>
                <form action="MissionUpdate" method="post">
                    <select name="Status" onchange="this.form.submit()">
                        <option  value ="0">Created</option>
                        <option selected value = "1">In progress</option>
                        <option value = "2">Finished</option>
                    </select>
                    <input type="hidden" name="MissionId" value="<%=mis.get(i).getId()%>">
                </form>
                <%} else {%>
                    <form action="MissionUpdate" method="post">
                        <select name="Status" onchange="this.form.submit()">
                        <option value ="0">Created</option>
                        <option selected value = "1">In progress</option>
                        <option value = "2">Finished</option>
                    </select>
                        <input type="hidden" name="MissionId" value="<%=mis.get(i).getId()%>">
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
