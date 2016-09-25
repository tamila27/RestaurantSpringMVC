<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GRAPES</title>

    <spring:url value="/resources/bootstrap/css/bootstrap.min.css"
                var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>

    <spring:url value="/resources/jquery/jquery-3.1.0.min.js"
                var="jqueryJs"/>
    <script src="${jqueryJs}"></script>
</head>
<body>
<div class="container">

    <nav class="navbar navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">GRAPES</a>
            </div>
        </div>
    </nav>
    <ul id="btnsList" class="nav nav-pills">
        <li id="btnMenu" <c:if test="${active == 'menu'}">class="active"</c:if> ><a href="/adminmenu">Menu</a></li>
        <li id="btnDish" <c:if test="${active == 'dish'}">class="active"</c:if> ><a href="/admindish">Dish</a></li>
        <li id="btnEmployee"  <c:if test="${active == 'employee'}">class="active"</c:if>><a href="/adminemployee">Employee</a></li>
        <li id="btnStorage" <c:if test="${active == 'storage'}">class="active"</c:if>><a href="/adminstorage">Storage</a></li>
        <li id="btnOrder" <c:if test="${active == 'order'}">class="active"</c:if>><a href="/adminorder">Order</a></li>
    </ul>
</div>

<br/>
</body>