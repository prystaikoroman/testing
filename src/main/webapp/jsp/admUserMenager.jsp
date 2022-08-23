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

    <script LANGUAGE="JavaScript">
        function validation() {
            var x = document.formUsers["login"].value;
            if (x == null || x === "") {
                alert("username cannot be empty..!!");
                return false;
            } else {
                document.formUsers.update();
            }
        }



    </script>
</head>
<body>
<div style="text-align: center;"><h2>User Maneger</h2></div>

<%--Welcome <%=request.getAttribute("login") %>--%>
Welcome ${login}
<br>
<lable style="alignment: right">records per page</lable>

<form style="alignment: right" action="${pageContext.request.contextPath}/adminUser?command=index"
      method="get">
    <select  name="recordsPerPage" >
            <option  value="5"  >5</option>
            <option  value="10" >10</option>
            <option  value="15" >15</option>
    </select>
    <input type="text" hidden name="command" value="index" >
    <input type="text" hidden name="currentPage" value="${currentPage}" >
    <input type="submit" value="Submit" />
</form>
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
    <% if (request.getAttribute("users") != null || application.getAttribute("users") != null) { %>


    <c:forEach var="user" items="${users}">
        <tr>
            <form name="formUsers"
                  action="${pageContext.request.contextPath}/adminUser?command=editUser&userId=${user.id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}"
                  onsubmit="return validation()"
                  method="post">
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
                           maxlength="150">
                <td><input type="checkbox" name="admin"
                           <c:if test="${user.admin == 'true'}">checked="checked"</c:if> /></td>
                <td><input type="checkbox" name="locked"
                           <c:if test="${user.locked == 'true'}">checked="checked"</c:if> /></td>
                <td><input type="submit" name="update" value="update"/></td>
            </form>

            <form name="formDelete"
                  action="${pageContext.request.contextPath}/adminUser?command=deleteUser&userId=${user.id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}"
                  method="post">
                <td><input type="submit" name="delete" value="delete"></td>
            </form>
        </tr>

    </c:forEach>


    <%} %>
    <form name="formAdd"
          action="${pageContext.request.contextPath}/adminUser?command=addUser&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}"
          method="post">
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


<nav aria-label="Navigation for Users">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link"
                                     href="<%=request.getContextPath()%>/adminUser?command=index&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item active"><a class="page-link">
                            ${i} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link"
                                             href="<%=request.getContextPath()%>/adminUser?command=index&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="<%=request.getContextPath()%>/adminUser?command=index&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>


<div style="text-align: left"><a href="<%=request.getContextPath()%>/jsp/adminIndex.jsp">Admin Home</a></div>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>
</body>
</html>
