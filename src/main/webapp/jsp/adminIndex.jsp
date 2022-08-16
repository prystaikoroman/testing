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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <title>Admin Page</title>
    <%
        if ((request.getSession(false).getAttribute("Admin") == null)) {
    %>
    <jsp:forward page="/jsp/login.jsp"></jsp:forward>
    <%} %>
</head>
<body>
<div style="text-align: center;"><h2>Admin's Home</h2></div>

<%--Welcome <%=request.getAttribute("login") %>--%>
Welcome ${login}


<div style="text-align: center">
    <h2><a href="<%=request.getContextPath()%>/adminUser?command=index&recordsPerPage=5&currentPage=1">User Menager</a></h2>
</div>
<div style="text-align: center">
    <h2><a href="<%=request.getContextPath()%>/adminSubject?command=index&recordsPerPage=5&currentPage=1">Subject Manage</a></h2>
</div>

<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>

</body>
</html>
