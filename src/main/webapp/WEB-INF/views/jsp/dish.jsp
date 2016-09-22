<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${dish.name}</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">

    <h1>${dish.name}</h1>

    <h2>Weight : ${dish.weight}</h2>
    <h2>Price : ${dish.price} </h2>

    <ul>
        <c:forEach items="${dish.ingredients}" var="ingredient">
            <li>${ingredient.ingredient.title}</li>
        </c:forEach>
    </ul>

</div>

</body>
</html>
