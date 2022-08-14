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
        if ((request.getSession(false).getAttribute("Admin") == null)) {
    %>
    <jsp:forward page="/jsp/login.jsp"></jsp:forward>
    <%} %>
</head>
<body>
<div style="text-align: center;"><h2>Admin's Home</h2></div>

<%--Welcome <%=request.getAttribute("login") %>--%>
Welcome ${login}
<table class="table">
    <tr>
        <td><label>login</label></td>
        <td><label>email</label></td>
        <td><label>lastname</label></td>
        <td><label>password</label></td>
        <td><label>admin</label></td>
        <td><label>locked</label></td>
    </tr>

<%--    <% if (request.getAttribute("users") != null) {   %>  --%>
    <% if (request.getAttribute("users") != null ||  application.getAttribute("users") != null) {  %>


    <c:forEach var="user" items="${users}">
        <tr>
            <form name="form1" action="${pageContext.request.contextPath}/editUser?userId=${user.id}" method="post">
                <td><input type="text" name="login" value="${user.login}"
                           oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                           maxlength="150"></td>
                <td><input type="text" name="email" value="${user.email}"
                           oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                           maxlength="150"></td>
                <td><input type="text" name="lastname" value="${user.lastname}"
                           oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                           maxlength="45"></td>
                <td><input type="text" name="password" value="${user.password}"
                           oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                           maxlength="150"></td>
                <td><input type="checkbox"  name="admin" <c:if test="${user.admin == 'true'}">checked="checked"</c:if> /></td>
                <td><input type="checkbox"  name="locked" <c:if test="${user.locked == 'true'}">checked="checked"</c:if> /></td>
                <td><input type="submit" name="update" value="update"/></td>
            </form>

            <form name="form2" action="${pageContext.request.contextPath}/deleteUser?userId=${user.id}" method="post">
                <td><input type="submit" name="delete" value="delete"></td>
            </form>
        </tr>

    </c:forEach>


    <%} %>
    <form name="formAdd" action="${pageContext.request.contextPath}/addUser" method="post">
        <tr>
            <td><input type="text" name="login"
                       oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                       maxlength="150"></td>
            <td><input type="text" name="email"
                       oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                       maxlength="150"></td>
            <td><input type="text" name="lastname"
                       oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                       maxlength="45"></td>
            <td><input type="text" name="password"
                       oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                       maxlength="150"></td>
            <td><input type="checkbox" name="admin"/></td>
            <td><input type="checkbox" name="locked"/></td>

            <td><input type="submit" value="Add"/></td>
        </tr>
    </form>
</table>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>

</body>
</html>
