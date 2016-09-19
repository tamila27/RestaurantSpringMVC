<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GRAPES</title>
</head>
<body>
<h1>GRAPES</h1>

<a href="/tables">Tables schema</a>

<form action="/dish" method="post">
    Search dish: <input type="text" name="dishName"/>
    <input type="submit" value="Search">
</form>
<ul style="align-items: center">
    <c:forEach items="${menuList}" var="menu">
        <li><h1>${menu.name}</h1>

            <table style="align-items: center">
                <tr>
                    <th>Dish Name</th>
                    <th>Weight</th>
                    <th>Price</th>
                </tr>
                <c:forEach items="${menu.dishes}" var="dish">
                    <tr>
                        <td><a href="/dish/${dish.name}">${dish.name}</a> </td>
                        <td>${dish.weight}</td>
                        <td>${dish.price}</td>
                    </tr>
                </c:forEach>
            </table>
        </li>
    </c:forEach>
</ul>
</body>
</html>
