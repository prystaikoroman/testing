<%--
  Created by IntelliJ IDEA.
  User: romap
  Date: 25.08.2022
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel='stylesheet' type='text/css' href="${pageContext.request.contextPath}/css/page.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">

    <title>
        <% if (session.getAttribute("language")!= null && session.getAttribute("language").equals("EN")  )
        { %>Registration<%} else { %>Реєстрація<%} %>
        </title>

</head>
<body>
<form action="${pageContext.request.contextPath}/adminUser?recordsPerPage=15&currentPage=1"
      method="post">
    <div align="center">
        <h1>
        <% if (session.getAttribute("language")!= null && session.getAttribute("language").equals("EN")  )
        { %>New User:<%} else { %>Новий користувач:<%} %>
    </h1>
        <input type="hidden" name="command" value="registerUser">
        <input type="hidden" name="admin" value="0">
        <input type="hidden" name="locked" value="0">

        <label for="login">
            <% if (session.getAttribute("language")!= null && session.getAttribute("language").equals("EN")  )
            { %>Login :<%} else { %>Логін :<%} %>
            </label> <br>
        <input id="login" type="text" name="login"> <br>

        <label for="email">
            <% if (session.getAttribute("language")!= null && session.getAttribute("language").equals("EN")  )
            { %>Email :<%} else { %>Електронна скринька :<%} %>
            </label> <br>
        <input id="email" type="text" name="email"> <br>

        <label for="lastname">
            <% if (session.getAttribute("language")!= null && session.getAttribute("language").equals("EN")  )
            { %>Lastname :<%} else { %>Прізвище :<%} %>
            </label> <br>
        <input id="lastname" type="text" name="lastname"> <br>

         <label for="pw">
             <% if (session.getAttribute("language")!= null && session.getAttribute("language").equals("EN")  )
             { %>Password :<%} else { %>Пароль :<%} %>
             </label> <br>
        <input id="pw" type="password" name="password"> <br>

        <input type="submit" value="register">

    </div>
</form>

</body>
</html>
