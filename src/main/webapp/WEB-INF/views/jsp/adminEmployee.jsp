<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee</title>
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
        <div id="employeesList" class=" text-right  col-md-6">
            <table class="table table-bordered" id="employeeTable">
                <c:forEach items="${employees}" var="employee">
                    <tr data-id="${employee.id}" data-name="${employee.name}"
                        data-lastName="${employee.lastName}" data-birthDate="${employee.birthDate}"
                        data-phone="${employee.phone}" data-position="${employee.position}"
                        data-salary="${employee.salary}" class="clickable-row">
                        <td width="100%">${employee.name}</td>
                            <%--<td>
                                <a id="editMenuLink" data-id="${menu.id}" data-name="${menu.name}" href="#"
                                   class="edit-menu">
                                    <span class="glyphicon glyphicon-edit"></span>
                                </a>
                            </td>
                            <td>
                                <a id="deleteMenuLink" data-id="${menu.id}" data-name="${menu.name}" href="#"
                                   class="delete-menu">
                                    <span class="glyphicon glyphicon-remove-circle"></span>
                                </a>
                            </td>--%>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="employeeInfo" class=" text-left col-md-6">
            <div class="form-group">
                <label class="control-label">Name:</label><input id="tiName" class="form-control" name="name"
                                                                 type="text"/>
            </div>
            <div class="form-group">
                <label class="control-label">Last Name:</label><input id="tiLastName" class="form-control"
                                                                      name="lastName"
                                                                      type="text"/>
            </div>
            <div class="form-group">
                <label class="control-label">Birth Date:</label><input id="tiBirthDate" class="form-control"
                                                                       name="birthDate"
                                                                       type="text"/>
            </div>
            <div class="form-group">
                <label class="control-label">Phone:</label><input id="tiPhone" class="form-control" name="phone"
                                                                  type="text"/>
            </div>
            <div class="form-group">
                <label class="control-label">Position:</label>
                <select class="form-control" id="tiPosition">
                    <c:forEach items="${positions}" var="position">
                        <option>${position}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label class="control-label">Salary:</label><input id="tiSalary" class="form-control" name="salary"
                                                                   type="text"/>
            </div>
            <div class="form-group">
                <input class="btn btn-primary" type="submit" value="Update" onclick="onUpdateBtnClick(event)">
            </div>
        </div>
    </div>
</div>
<script>
    var employeeId;
    var employeename;
    var lastname;
    var birthdate;
    var phone;
    var employeeposition;
    var salary;
    jQuery(document).ready(function ($) {


        $('#employeeTable').on('click', '.clickable-row', function (event) {
            if ($(this).hasClass('active')) {
            } else {
                $(this).addClass('active').siblings().removeClass('active');
                $('#tiName').val($(this).data('name'));
                $('#tiLastName').val($(this).data('lastname'));
                $('#tiBirthDate').val($(this).data('birthdate'));
                $('#tiPhone').val($(this).data('phone'));
                $('#tiPosition').val($(this).data('position'));
                $('#tiSalary').val($(this).data('salary'));

                employeeId = $(this).data('id');
                employeename = $(this).data('name');
                lastname = $(this).data('lastname');
                birthdate = $(this).data('birthdate');
                phone = $(this).data('phone');
                employeeposition = $(this).data('position');
                salary = $(this).data('salary');
            }
        });
    });

    function onUpdateBtnClick(event) {

        employeename = $('#tiName')[0].value;
        lastname = $('#tiLastName')[0].value;
        birthdate = $('#tiBirthDate')[0].value;
        phone = $('#tiPhone')[0].value;
        employeeposition = $('#tiPosition')[0].value;
        salary = $('#tiSalary')[0].value;

        $.ajax({
            type: "PUT",
            url: "/employee/update/" + employeeId +"/"+employeename +"/"+lastname +"/"+birthdate +"/"+phone +"/"+employeeposition +"/"+salary,
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                location.reload();
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        });
    }
</script>
</body>
</html>
