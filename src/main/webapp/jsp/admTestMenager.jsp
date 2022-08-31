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
    <title>Test Manage</title>
    <%--    <%--%>
    <%--        if ((request.getSession(false).getAttribute("Admin") == null)) {--%>
    <%--    %>--%>
    <%--    <jsp:forward page="/jsp/login.jsp"></jsp:forward>--%>
    <%--    <%} %>--%>

    <script LANGUAGE="JavaScript">
        function validation() {
            var x = document.formTests["name"].value;
            if (x == null || x === "") {
                alert("name cannot be empty..!!");
                return false;
            } else {
                document.formTests.update();
            }

        }
    </script>
</head>
<body>
<div style="text-align: center;"><h2>Test Maneger</h2></div>

<%--Welcome <%=request.getAttribute("login") %>--%>
Welcome ${login}
<br>
<lable style="alignment: right">records per page</lable>

<form style="alignment: right" action="${pageContext.request.contextPath}/adminTest?command=index"
      method="get">
    <select name="recordsPerPage">
        <option value="5">5</option>
        <option value="10">10</option>
        <option value="15">15</option>
    </select>
    <input type="text" hidden name="command" value="index">
    <input type="text" hidden name="currentPage" value="${currentPage}">
    <input type="text" hidden name="subject_Id" value="${subject_Id}">
    <input type="submit" value="Submit"/>
</form>
<table class="table">
    <tr>
        <td><label>Task</label></td>
        <td><label>Name</label></td>
        <td><label>Difficulty</label></td>
        <td><label>Passing Time (minute)</label></td>
        <td><label>finished</label></td>
    </tr>

    <%--    <% if (request.getAttribute("users") != null) {   %>  --%>
    <% if (request.getAttribute("tests") != null || application.getAttribute("tests") != null) { %>


    <c:forEach var="test" items="${tests}">
        <tr>
            <form name="formTests"
                  action="${pageContext.request.contextPath}/adminTest?command=editTest&testId=${test.id}&subject_Id=${subject_Id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}"
                  onsubmit="return validation()"
                  method="post">
                <td><input type="text" name="task" value="${test.task}"
                        <%if ((request.getSession(false).getAttribute("Admin") == null)) {%>
                           readonly
                        <%} %>
                           oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                           maxlength="250"></td>
                <td><input type="text" name="name" value="${test.name}"
                        <%if ((request.getSession(false).getAttribute("Admin") == null)) {%>
                           readonly
                        <%} %>
                           oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                           maxlength="45"></td>
                <td><input type="text" name="difficulty" value="${test.difficulty}"
                        <%if ((request.getSession(false).getAttribute("Admin") == null)) {%>
                           readonly
                        <%} %>
                           oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                           maxlength="10"></td>
                <td><input type="text" name="passingTimeMin" value="${test.passingTimeMin}"
                        <%if ((request.getSession(false).getAttribute("Admin") == null)) {%>
                           readonly
                        <%} %>
                           oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                ></td>
                <%if ((request.getSession(false).getAttribute("Admin") == null)) {%>
                <td><input type="text" name="percent_result" value="${test.percent_result}"
                           readonly
                ></td>

                <td><input type="checkbox" name="finished"
                           onclick="return false;"
                <c:if test="${test.finished == 'true'}">
                           checked="checked"
                </c:if>
                    <%--                               value="${test.finished}"--%>
                           readonly
                ></td>
                <%} %>
                <%if ((request.getSession(false).getAttribute("Admin") != null)) {%>
                <td><input type="submit" name="update" value="update"/></td>
                <%} %>
            </form>
            <%if ((request.getSession(false).getAttribute("Admin") == null)) {%>

            <c:if test="${test.finished == 'false'}">
                <form name="formTestQueries"
                      action="${pageContext.request.contextPath}/adminQuerie?command=userPassing&test_Id=${test.id}&subject_Id=${subject_Id}&recordsPerPage=1&currentPage=1"
                      method="post">
                    <td><input type="submit" name="queries" value="Pass this Test"></td>
                </form>
            </c:if>
            <%} else {%>
            <form name="formTestQueries"
                  action="${pageContext.request.contextPath}/adminQuerie?command=index&test_Id=${test.id}&subject_Id=${subject_Id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}"
                  method="post">
                <td><input type="submit" name="queries" value="Test Queries"></td>
            </form>
            <form name="formDelete"
                  action="${pageContext.request.contextPath}/adminTest?command=deleteTest&testId=${test.id}&subject_Id=${subject_Id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}"
                  method="post">
                <td><input type="submit" name="delete" value="delete"></td>
            </form>
            <%} %>
        </tr>

    </c:forEach>
    <%} %>

    <%if ((request.getSession(false).getAttribute("Admin") != null)) {%>
    <form name="formAdd"
          action="${pageContext.request.contextPath}/adminTest?command=addTest&subject_Id=${subject_Id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage}"
          method="post">
        <tr>
            <td><input type="text" name="task"
                       oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                       maxlength="250"></td>
            <td><input type="text" name="name"
                       oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                       maxlength="45"></td>
            <td><input type="text" name="difficulty"
                       oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                       maxlength="10"></td>
            <td><input type="text" name="passingTimeMin"
                       oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
                       maxlength="3"></td>
            ></td>
            <td><input type="submit" value="Add"/></td>
        </tr>
    </form>
    <%} %>
</table>


<nav aria-label="Navigation for Tests">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link"
                                     href="<%=request.getContextPath()%>/adminTest?command=index&subject_Id=${subject_Id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
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
                                             href="<%=request.getContextPath()%>/adminTest?command=index&subject_Id=${subject_Id}&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="<%=request.getContextPath()%>/adminTest?command=index&subject_Id=${subject_Id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>


<div style="text-align: left"><a
        href="<%=request.getContextPath()%>/adminSubject?command=index&subject_Id=${subject_Id}&recordsPerPage=5&currentPage=1">Subject
    Home</a></div>

</body>
</html>
