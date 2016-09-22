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
        <li id="btnHome" <c:if test="${active == 'home'}">class="active"</c:if> ><a href="/">Home</a></li>
        <li id="btnTables" <c:if test="${active == 'tables'}">class="active"</c:if> ><a href="/tables">Tables schema</a></li>
        <li id="btnWaiters"  <c:if test="${active == 'waiters'}">class="active"</c:if>><a href="/waiters">Waiters</a></li>
        <li id="btnContacts" <c:if test="${active == 'contacts'}">class="active"</c:if>><a href="/contacts">Contacts</a></li>
        <li id="btnLogin" <c:if test="${active == 'login'}">class="active"</c:if>><a href="/login">Login</a></li>
    </ul>
</div>

<br/>
<script>
    function onBtnClick(button) {
        for(var i = 0; i< $("#btnsList").children.length; i++) {
            $("#btnsList").children[i].setAttribute("class", "");
        }
        button.setAttribute("class", "active");
    }
</script>
</body>