<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Waiters</title>
</head>
<body>
<table style="align-items: center">
    <tr>
        <th>Photo</th>
        <th>First Name</th>
    </tr>
    <c:forEach items="${waiters}" var="waiter">
        <tr>
            <td><img width="100" height="100" src="<c:url value="/images/smile.jpg" />"/></td>
            <td>${waiter.name} </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
