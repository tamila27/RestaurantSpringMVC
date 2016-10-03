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
<jsp:include page="header.jsp"/>
<div class="container">

    <div class="container">
        <div class="jumbotron row">
            <div id="dishInfo" class=" text-left col-md-6">

            </div>

            <div class=" text-right col-md-6">
                Search dish: <input id="dishName" type="text" name="dishName"/>
                <button id="btnSearch" type="submit" class="btn btn-default btn-sm">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span> Search
                </button>
            </div>
        </div>
    </div>


    <ul style="align-items: center">
        <c:forEach items="${menuList}" var="menu">
            <li><h1>${menu.name}</h1>


                <table class="table table-bordered">
                    <tr>
                        <th>Dish Name</th>
                        <th>Weight</th>
                        <th>Price</th>
                    </tr>
                    <c:forEach items="${menu.dishes}" var="dish">
                        <tr>
                            <td><a class="dishLink" href="#">${dish.name}</a></td>
                            <td>${dish.weight}</td>
                            <td>${dish.price}</td>
                        </tr>
                    </c:forEach>
                </table>
            </li>
        </c:forEach>
    </ul>

    <script>
        jQuery(document).ready(function ($) {

            $("btn").on("click", function (e) {
                var dishName = $("#dishName").val();
                $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "/dish/" + dishName,
                    //data : JSON.stringify(),
                    dataType: 'json',
                    timeout: 100000,
                    success: function (data) {
                        console.log("SUCCESS: ", data);
                        buildEmployeeTable(data);
                    },
                    error: function (e) {
                        console.log("ERROR: ", e);
                        display(e);
                    },
                    done: function (e) {
                        console.log("DONE");
                        enableSearchButton(true);
                    }
                });
            });

            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "/employee/",
                //data : JSON.stringify(),
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    console.log("SUCCESS: ", data);
                    buildEmployeeTable(data);
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                    display(e);
                },
                done: function (e) {
                    console.log("DONE");
                    enableSearchButton(true);
                }
            });

            buildEmployeeTable = function (employeeList) {
                var emplyeeTable = $("#emplyeeTable");
                if (emplyeeTable != null && emplyeeTable != undefined) {
                    for (var i = 0; i < employeeList.length; i++) {
                        var trHtml = '<tr><td><a href="/employee/' + employeeList[i].name + '">' + employeeList[i].name + '</a> </td>' +
                                '<td>' + employeeList[i].position + '</td></tr>';
                        emplyeeTable.append(trHtml);
                    }

                } else {
                    console.log("Failed to find element ");
                }
            };

            $(".dishLink").on("click", function (e) {
                var dishName = $(this).text();
                getDishInfo(dishName);
            });

            $("#btnSearch").on("click", function (e) {
                var dishName = $("#dishName").val();
                getDishInfo(dishName);
            });

            function getDishInfo(dishName) {

                $.ajax({
                    type: "GET",
                    //contentType: "application/json",
                    url: "/dish/" + dishName,
                    //data : JSON.stringify(),
                    dataType: 'json',
                    timeout: 100000,
                    success: function (data) {
                        console.log("SUCCESS: ", data);
                        buidDishInfo(data);
                    },
                    error: function (e) {
                        console.log("ERROR: ", e);
                        display(e);
                    },
                    done: function (e) {
                        console.log("DONE");
                        enableSearchButton(true);
                    }
                });
            }

            buidDishInfo = function (dish) {
                var dishInfo = $("#dishInfo");
                if (dishInfo != null && dishInfo != undefined) {
                    dishInfo.empty();
                    var dishInfoHtml = '<h1>' + dish.name + '</h1>';
                    dishInfoHtml += '<h2> Weight: ' + dish.weight + '</h2>';
                    dishInfoHtml += '<h2> Price: ' + dish.price + '</h2> <ul>';
                    for (var i = 0; i < dish.ingredients.length; i++) {
                        dishInfoHtml += '<li>' + dish.ingredients[i].ingredient.title + '</li>';
                    }
                    dishInfoHtml += '</ul>';

                    dishInfo.append(dishInfoHtml);

                }
            }


        });

    </script>
</div>
</body>
</html>
