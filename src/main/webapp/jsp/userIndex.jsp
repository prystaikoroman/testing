<%--
  Created by IntelliJ IDEA.
  User: romap
  Date: 09.08.2022
  Time: 1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="m" %>
<html>
<head>
    <title>User Page</title>
    <link rel='stylesheet' type='text/css' href="${pageContext.request.contextPath}/css/page.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <% //In case, if User session is not set, redirect to Login page.
        if((request.getSession(false).getAttribute("User")== null) )
        {
    %>
    <jsp:forward page="/jsp/login.jsp"></jsp:forward>
    <%} %>
</head>
<body>
<div style="text-align: center;"><h2>User's Home</h2></div>
Welcome ${login}
<br>
Current Date and Time is: <m:today/>
<div style="text-align: center">
    <h2><a href="<%=request.getContextPath()%>/adminSubject?command=index&recordsPerPage=5&currentPage=1">Subject for pass</a></h2>
</div>

<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>

</body>
</html>
