<%--
  Created by IntelliJ IDEA.
  User: romap
  Date: 09.08.2022
  Time: 1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel='stylesheet' type='text/css' href="${pageContext.request.contextPath}/css/page.css">
    <title>Admin Page</title>
        <%
if((request.getSession(false).getAttribute("Admin")== null) )
{
%>
    <jsp:forward page="/jsp/login.jsp"></jsp:forward>
        <%} %>
</head>
<body>
<div style="text-align: center;"><h2>Admin's Home</h2></div>

Welcome <%=request.getAttribute("login") %>
<% if (request.getAttribute("users") != null){
%>
<table class="table" >
<c:forEach var="user" items="${users}">
    <tr>
        <form name="form1" action="${pageContext.request.contextPath}/editUser?userId=${user.id}" method="post">
            <td><input type="text" name="login" value="${user.login}" maxlength="150"  ></td>
            <td><input type="text" name="email" value="${user.email}"   maxlength="150"  ></td>
            <td><input type="text" name="lastname" value="${user.lastname}"  maxlength="45"   ></td>
            <td><input type="text" name="password" value="${user.password}" maxlength="150"  ></td>
            <td><input type="checkbox" name="admin" checked="${user.admin}" /> </td>
            <td><input type="checkbox" name="locked" checked="${user.locked}" /> </td>

            <td><input type="submit" value="update" /></td>

        </form>
    </tr>

</c:forEach>

</table>
<%} %>


<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>

</body>
</html>
