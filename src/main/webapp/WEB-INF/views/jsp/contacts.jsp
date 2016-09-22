<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contacts</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    ул. Евгения Коновальца 36Д
    (бывш. Щорса) 8й этаж
    Киев, Украина
    E-mail: info@goit.ua
    Cell: +38 093 343 21 27
    <br/>
    <br/>
    <img src="<c:url value="/images/contacts.png" />"/>
</div>
</body>
</html>
