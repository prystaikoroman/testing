<%--
  Created by IntelliJ IDEA.
  User: romap
  Date: 08.08.2022
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel='stylesheet' type='text/css' href="${pageContext.request.contextPath}/css/page.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">

    <title>Please login!</title>
    <form name="form" action="<%=request.getContextPath()%>/LoginServlet?command=login" method="post">
        <table class="table" >
            <tr>
                <td>login</td>
                <td><input type="text" name="login" value="${login}"/></td>
            </tr>
            <tr>
                <td>password</td>
                <td><input type="password" name="password" value="${password}"/></td>
            </tr>
            <tr>
                <td><span class="span" ><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Login"></input>
                    <input type="reset" value="Reset"></input></td>
            </tr>

        </table>
    </form>
    <div style="text-align: center">
        <h2><a href="<%=request.getContextPath()%>/LoginServlet?command=register">registration</a></h2>
    </div>
</head>
<body>

</body>
</html>
