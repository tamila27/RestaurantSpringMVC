<%--@elvariable id="employee" type="com.goit.gojavaonline.spring.mvc.model.Employee"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>
<table style="align-items: center">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Position</th>
        <th>Phone Number</th>
        <th>Salary</th>

    </tr>

    <tr>
        <td>${employee.name}</td>
        <td>${employee.lastName}</td>
        <td>${employee.position}</td>
        <td>${employee.salary}</td>
        <td>${employee.phone}</td>
    </tr>

</table>
</body>
</html>
