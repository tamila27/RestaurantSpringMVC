<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In Page</title>
</head>
<body>

<jsp:include page="header.jsp"/>
<div class="container">
    <%--<h1>Hello Goit. It is now: ${currentTime} </h1>--%>
    <form action="/sign-in" method="post">
        <div class="form-group">
            <label class="control-label">Username:</label><input class="form-control" name="username" type="text"/>
        </div>
        <div class="form-group">
            <label class="control-label">Password:</label><input class="form-control" name="password" type="password"/>
        </div>
        <div class="form-group">
            <input class="btn btn-primary" type="submit" value="Sign In">
        </div>
    </form>
</div>

</body>
</html>

