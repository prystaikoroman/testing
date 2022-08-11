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

<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>

</body>
</html>
