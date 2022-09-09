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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script language="JavaScript">
        function setLanguageEN(){
            console.log('entered!!');
            $.ajax({
                type: 'GET',
                url: 'AjaxLanguageSetServlet?',
                data: { language : "EN"  },
                success    : function(resultText){
                    $('#result').html(resultText);
                },
                error : function(jqXHR, exception) {
                    console.log('Error occured!!');
                }
            });
            location.reload(true);
        }

    function setLanguageUA(){
        console.log('entered!!');
        $.ajax({
            type: 'GET',
            url: 'AjaxLanguageSetServlet',
            data: { language : "UA"  }
        });
        location.reload(true);
<%--            <%  request.getSession();--%>
<%--            session.setAttribute("language",value); %>--%>
<%--            location.reload(true);--%>
<%--            <jsp:forward page="/jsp/login.jsp"></jsp:forward>--%>
        }

        function validateFields()
        {
            if((document.getElementsById('login').value.trim()!="")
                &&(document.getElementsById('login').value.length>=4)
                &&(document.getElementsById('password').value.length>=8)
                )
            {
                //additional validation on server side
                $.ajax({
                    type: 'GET',
                    url: 'AjaxLoginValidation?',
                    data: { login : document.getElementsById('login').value  },
                    success    : function(resultText){
                        $('#result').html(resultText);
                    },
                    error : function(jqXHR, exception) {
                        console.log('Error occured!!');
                    }
                });

            }
        else
            {
                alert("validation failed");
                event.preventDefault();
                return false;
            }
        } //end of function
</script>
    <lable onclick="setLanguageEN()">EN</lable>
    <lable onclick="setLanguageUA()">UA</lable>
    <title>
        <% if (session.getAttribute("language")!= null && session.getAttribute("language").equals("EN")  ) { %>
        Please login!
        <%} else { %>
        Будь ласка авторизуйтеся!
        <%} %>
    </title>
    <form name="form" action="<%=request.getContextPath()%>/LoginServlet?command=login" onsubmit="validateFields()" method="post">

        <table class="table" >
            <tr>
                <td>
                    <% if (session.getAttribute("language")!= null && session.getAttribute("language").equals("EN")  )
                    { %>login<%} else { %>логін<%} %>
                    </td>
                <td><input type="text" id="login" name="login" value="${login}"/></td>
            </tr>
            <tr>
                <td>
                    <% if (session.getAttribute("language")!= null && session.getAttribute("language").equals("EN")  )
                    { %>password<%} else { %>пароль<%} %>
                </td>
                <td><input type="password" id="password" name="password" value="${password}"/></td>
            </tr>
            <tr>
                <td><span class="span" ><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Login"></input>
                    <input type="reset" value="Reset"></input>
                </td>
            </tr>

        </table>
    </form>
    <div style="text-align: center">
<%--        <h2><a href="<%=request.getContextPath()%>/LoginServlet?command=register">--%>
        <h2><a href="${pageContext.request.contextPath}/jsp/registration.jsp">
            <% if (session.getAttribute("language")!= null && session.getAttribute("language").equals("EN")  )
            { %>registration<%} else { %>реєстрація<%} %>
        </a></h2>
    </div>
</head>
<body>

</body>
</html>
