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
    <title>Answer Manage</title>
    <%
        if ((request.getSession(false).getAttribute("Admin") == null)) {
    %>
    <jsp:forward page="/jsp/login.jsp"></jsp:forward>
    <%} %>

    <script LANGUAGE="JavaScript">
        function validation() {
            var x = document.formAnswers["answer"].value;
            if (x == null || x === "") {
                alert("answer cannot be empty..!!");
                return false;
            } else {
                document.formAnswers.update();
            }

        }
    </script>
</head>
<body>
<div style="text-align: center;"><h2>Answer Maneger</h2></div>

<%--Welcome <%=request.getAttribute("login") %>--%>
Welcome ${login}
<br>
<lable style="alignment: right">records per page</lable>

<form style="alignment: right" action="${pageContext.request.contextPath}/adminAnswer?command=index"
      method="get">
    <select  name="recordsPerPage" >
        <option  value="5"  >5</option>
        <option  value="10" >10</option>
        <option  value="15" >15</option>
    </select>
    <input type="text" hidden name="command" value="index" >
    <input type="text" hidden name="currentPage" value="${currentPage}" >
    <input type="text" hidden name="querie_Id" value="${querie_Id}" >
    <input type="text" hidden name="test_Id" value="${test_Id}" >
    <input type="text" hidden name="subject_Id" value="${subject_Id}" >
    <input type="submit" value="Submit" />
</form>
<table class="table">
    <tr>
        <td><label>Answer</label></td>
        <td><label>Correct</label></td>
    </tr>

    <%--    <% if (request.getAttribute("users") != null) {   %>  --%>
    <% if (request.getAttribute("answers") != null || application.getAttribute("answers") != null) { %>


    <c:forEach var="answer" items="${answers}">
        <tr>
            <form name="formAnswers"
                  action="${pageContext.request.contextPath}/adminAnswer?command=editAnswer&answerId=${answer.id}&querie_Id=${querie_Id}&test_Id=${test_Id}&subject_Id=${subject_Id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}"
                  onsubmit="return validation()"
                  method="post">
                <td><textarea name="answer" cols="40" rows="5"
                          oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                          maxlength="150">${answer.answer}</textarea></td>
                <td><input type="checkbox" name="correct"
                           <c:if test="${answer.correct == 'true'}">checked="checked"</c:if> /></td>
                  <td><input type="submit" name="update" value="update"/></td>
            </form>

            <form name="formDelete"
                  action="${pageContext.request.contextPath}/adminAnswer?command=deleteAnswer&answerId=${answer.id}&querie_Id=${querie_Id}&test_Id=${test_Id}&subject_Id=${subject_Id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}"
                  method="post">
                <td><input type="submit" name="delete" value="delete"></td>
            </form>
        </tr>

    </c:forEach>


    <%} %>
    <form name="formAdd"
          action="${pageContext.request.contextPath}/adminAnswer?command=addAnswer&querie_Id=${querie_Id}&test_Id=${test_Id}&subject_Id=${subject_Id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}"
          method="post">
        <tr>
            <td><textarea name="answer" cols="40" rows="5"
                          oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                          maxlength="150"></textarea></td>
            <td><input type="checkbox" name="correct" /></td>
<%--            <td><input class="largeTextInput"  type="text" name="question"--%>
<%--                       oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"--%>
<%--                       maxlength="150"></td>--%>
                ></td>
            <td><input type="submit" value="Add"/></td>
        </tr>
    </form>
</table>


<nav aria-label="Navigation for Answers">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link"
                                     href="<%=request.getContextPath()%>/adminAnswer?command=index&querie_Id=${querie_Id}&test_Id=${test_Id}&subject_Id=${subject_Id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
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
                                             href="<%=request.getContextPath()%>/adminAnswer?command=index&querie_Id=${querie_Id}&test_Id=${test_Id}&subject_Id=${subject_Id}&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="<%=request.getContextPath()%>/adminAnswer?command=index&querie_Id=${querie_Id}&test_Id=${test_Id}&subject_Id=${subject_Id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>


<div style="text-align: left"><a href="<%=request.getContextPath()%>/adminQuerie?command=index&test_Id=${test_Id}&subject_Id=${subject_Id}&recordsPerPage=5&currentPage=1">Querie Home</a></div>

</body>
</html>
