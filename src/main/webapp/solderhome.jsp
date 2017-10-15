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
    <title>Solder home</title>
</head>
<body>
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
            <td><%=mis.get(i).getId()%></td>
            <td><%=mis.get(i).getDesc()%></td>
            <td><% if(mis.get(i).getStatus()==0) { %>
                <select>
                    <option selected>Created</option>
                    <option>In progress</option>
                    <option>Finished</option>
                </select>
                <% } else
                    if (mis.get(i).getId()==1){%>
                    <select>
                        <option>Created</option>
                        <option selected>In progress</option>
                        <option>Finished</option>
                    </select>
                <%} else {%>
                    <select>
                        <option>Created</option>
                        <option>In progress</option>
                        <option selected>Finished</option>
                    </select>
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
