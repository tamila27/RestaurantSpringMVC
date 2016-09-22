<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>

<jsp:include page="header.jsp"/>
<div class="container">
    <table class="table">
        <tr>
            <th>Photo</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Position</th>
            <th>Salary</th>
        </tr>
        <c:forEach items="${employees}" var="employee">
            <tr>
                <td><img width="100" height="100" src="<c:url value="/images/smile.jpg" />"/></td>
                <td><a href="/employee/${employee.name}">${employee.name}</a></td>
                <td>${employee.lastName}</td>
                <td>${employee.position}</td>
                <td>${employee.salary}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
