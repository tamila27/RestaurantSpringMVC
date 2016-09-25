<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <spring:url value="/resources/bootstrap/css/bootstrap.min.css"
                var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>

    <spring:url value="/resources/jquery/jquery-3.1.0.min.js"
                var="jqueryJs"/>
    <script src="${jqueryJs}"></script>
</head>
<body>
<jsp:include page="adminHeader.jsp"/>
<div class="container">
    <div class="row">
        Search by:

        <input type="radio" name="radioName" value="1" checked="checked"/> waiter <br/>
        <input type="radio" name="radioName" value="2"/> table <br/>
        <input type="radio" name="radioName" value="3"/> date <br/>
        <input type="text" id="tiSearch"/>
        <button id="btnSearch" type="submit" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-search" aria-hidden="true"></span> Search
        </button>

    </div>
    <div id="tblAllOrders" class="row">
        <table class="table table-bordered" id="ordersTable">
            <tr>
                <th></th>
                <th>Order Date</th>
                <th>Table</th>
                <th>Waiter</th>
            </tr>
            <c:forEach items="${orders}" var="order">
                <tr data-id="${order.id}" data-employee="${order.employee}"
                    data-dishes="${order.dishes}" data-tablenum="${order.tableNum}"
                    data-orderdate="${order.orderDate}" data-closed="${order.closed}"
                    class="clickable-row">
                    <td>${order.id}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.tableNum}</td>
                    <td>${order.employee.name} ${order.employee.lastName}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div id="tblFoundOrders" class="row">
    </div>
    
</div>
<script>
    jQuery(document).ready(function ($) {

        $("#btnSearch").on("click", function (e) {
            if($('input[name=radioName]:checked').val() == 1) {
                searchByWaiter($('#tiSearch').val());
            } else if($('input[name=radioName]:checked').val() == 2) {
                searchByTable($('#tiSearch').val());
            } else {
                searchByDate($('#tiSearch').val());
            }
        });

        function searchByWaiter(waiterName) {
            $.ajax({
                type: "GET",
                url: "/order/waiter/" + waiterName,
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    console.log("SUCCESS: ", data);
                    buildFoundOrdersTable(data);
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                },
                done: function (e) {
                    console.log("DONE");
                }
            });
        }

        function searchByTable(tableNum) {
            $.ajax({
                type: "GET",
                url: "/order/table/" + tableNum,
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    console.log("SUCCESS: ", data);
                    buildFoundOrdersTable(data);
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                },
                done: function (e) {
                    console.log("DONE");
                }
            });
        }

        function searchByDate(date) {
            $.ajax({
                type: "GET",
                url: "/order/date/" + date,
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    console.log("SUCCESS: ", data);
                    buildFoundOrdersTable(data);
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                },
                done: function (e) {
                    console.log("DONE");
                }
            });
        }
    });

    function buildFoundOrdersTable(data) {
        $('#tblAllOrders').hide();
        $('#tblFoundOrders').show();
        var tableHtml = '<table class="table table-bordered" id="foundOrdersTable">'+
            '<tr>'+
                '<th></th>'+
                '<th>Order Date</th>'+
                '<th>Table</th>'+
                '<th>Waiter</th>'+
            '</tr>';
        for(var i = 0; i < data.length; i++) {
            tableHtml += '<tr class="clickable-row"> <td>'+ data[i].id+'</td>'+
                            '<td>'+ data[i].orderDate+'</td>'+
                            '<td>'+ data[i].tableNum+'</td>' +
                            '<td>'+ data[i].employee.name+' '+ data[i].employee.lastName+'</td> ' +
                    '</tr>'
        }
        tableHtml += '</table>';
        $('#tblFoundOrders').empty();
        $('#tblFoundOrders').append(tableHtml);
    }
</script>
</body>
</html>
