<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
        <div id="dishInfo" class=" text-right  col-md-6">
            <%--<div class="container text-right">--%>
            <%--<button type="button" class="btn btn-primary"
                    onclick="onAddMenuBtnClick()">Add Menu</button>
            <br/><br/>--%>
            <div class="row container text-right">
                <div class="row col-md-5">
                    <%--<label class="control-label">Menu name:</label>--%>
                    <input id="newMenuName" class="form-control"
                           type="text"/>

                </div>
                <div class="col-md-1">
                    <button class="btn btn-primary" type="submit" value="Add" onClick="addMenu()">Add</button>
                </div>
                <br/> <br/><br/>
            </div>
            <%--</div>--%>

            <table class="table table-bordered" id="menuTable">
                <c:forEach items="${menus}" var="menu">
                    <tr data-id="${menu.id}" data-name="${menu.name}" class="clickable-row">
                        <td width="100%">${menu.name}</td>
                        <td>
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
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div id="menuInfo" class=" text-left col-md-6" hidden="true">
            <div id="menuName"></div>
            <div class="row">
                <div id="menuDishes" class="col-md-5"></div>

                <div height="100%" id="editDishesButtons" class="col-md-2 text-center">
                    <p id="addDishLink" data-id="${menu.id}" data-name="${menu.name}" href="#">
                        <span class="glyphicon glyphicon-menu-left"></span>
                    </p>
                    <br/>
                    <br/>
                    <p id="deleteDishLink" data-id="${menu.id}" data-name="${menu.name}" href="#">
                        <span class="glyphicon glyphicon-menu-right"></span>
                    </p>
                </div>

                <div id="allDishes" class="col-md-5"></div>
            </div>
        </div>
    </div>
</div>

<script>
    var selectedMenuId;
    var selectedDishId_add;
    var selectedDishId_delete;

    jQuery(document).ready(function ($) {

        getAllDishes();

        $('#menuTable').on('click', '.clickable-row', function (event) {
            if ($(this).hasClass('active')) {
                //$(this).removeClass('active');
                //$('#menuInfo').empty();
            } else {
                $(this).addClass('active').siblings().removeClass('active');
                selectedMenuId = $(this).data('id');
                $('#menuInfo').show();
                var menuDetails = '<h2>' + $(this).data('name') + '</h2>';
                $('#menuName').empty();
                $('#menuName').append(menuDetails);
                getMenuDishes($(this).data('id'));
                //alert($(this).data('id'));
            }
        });

        $('#addDishLink').click(function (event) {
            addDishToMenu(selectedMenuId, selectedDishId_add);

        });

        $('#deleteDishLink').click(function (event) {
            deleteDishFromMenu(selectedMenuId, selectedDishId_delete);

        });

        $('#menuTable').on('click', ".edit-menu", function (event) {
            getAllDishes();

        });

        $('#menuTable').on('click', ".delete-menu", function (event) {
            //alert("Delete : " + $(this).data('name'));
            $.ajax({
                type: "DELETE",
                //contentType: "application/json",
                url: "/menu/delete/" + selectedMenuId,
                //data : JSON.stringify(),
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    console.log("SUCCESS: ", data);
                    location.reload();
                    //getMenuDishes(selectedMenuId);
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                    //display(e);
                },
                done: function (e) {
                    console.log("DONE");
                    //enableSearchButton(true);
                }
            });
        });
    });

    function getAllDishes() {

        $.ajax({
            type: "GET",
            //contentType: "application/json",
            url: "/dish",
            //data : JSON.stringify(),
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                buildAllDishesList(data);
            },
            error: function (e) {
                console.log("ERROR: ", e);
                //display(e);
            },
            done: function (e) {
                console.log("DONE");
                //enableSearchButton(true);
            }
        });
    }

    function getMenuDishes(menuId) {

        $.ajax({
            type: "GET",
            //contentType: "application/json",
            url: "/menu/" + menuId,
            //data : JSON.stringify(),
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                buildMenuDishesList(data);
            },
            error: function (e) {
                console.log("ERROR: ", e);
                //display(e);
            },
            done: function (e) {
                console.log("DONE");
                //enableSearchButton(true);
            }
        });
    }

    function addDishToMenu(menuId, dishId) {

        $.ajax({
            type: "PUT",
            //contentType: "application/json",
            url: "/menu/edit/" + menuId + "/" + dishId,
            //data : JSON.stringify(),
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                getMenuDishes(selectedMenuId);
            },
            error: function (e) {
                console.log("ERROR: ", e);
                //display(e);
            },
            done: function (e) {
                console.log("DONE");
                //enableSearchButton(true);
            }
        });
    }

    function deleteDishFromMenu(menuId, dishId) {

        $.ajax({
            type: "DELETE",
            //contentType: "application/json",
            url: "/menu/dish/delete/" + menuId + "/" + dishId,
            //data : JSON.stringify(),
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                getMenuDishes(selectedMenuId);
            },
            error: function (e) {
                console.log("ERROR: ", e);
                //display(e);
            },
            done: function (e) {
                console.log("DONE");
                //enableSearchButton(true);
            }
        });
    }

    function addMenu() {
        var menuName = $('#newMenuName')[0].value;
        //alert(menuName);
        $.ajax({
            type: "GET",
            //contentType: "application/json",
            url: "/menu/add/" + menuName,
            //data : JSON.stringify(),
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                location.reload();
                //getMenuDishes(data.id);
            },
            error: function (e) {
                console.log("ERROR: ", e);
                //display(e);
            },
            done: function (e) {
                console.log("DONE");
                //enableSearchButton(true);
            }
        });
    }

    function buildMenuDishesList(dishes) {
        var menuInfo = $('#menuDishes');
        if (menuInfo != null && menuInfo != undefined) {

            var menuInfoHtml = '<table id="menuDishesTable" class="table table-bordered">';
            for (var i = 0; i < dishes.length; i++) {
                menuInfoHtml += '<tr class="clickable-row" data-id="' + dishes[i].id + '"> <td>' + dishes[i].name + '</td></tr>';
            }
            menuInfoHtml += '</table>';
            menuInfo.empty();
            menuInfo.append(menuInfoHtml);

            $('#menuDishesTable').on('click', '.clickable-row', function (event) {
                if ($(this).hasClass('active')) {
                    //$(this).removeClass('active');
                    //$('#menuInfo').empty();
                } else {
                    $(this).addClass('active').siblings().removeClass('active');
                    selectedDishId_delete = $(this).data('id');

                    //getMenuDishes($(this).data('id'));
                    //alert(selectedDishId_delete);
                }
            });

        }
    }

    function buildAllDishesList(dishes) {
        var menuInfo = $('#allDishes');
        if (menuInfo != null && menuInfo != undefined) {
            var allDishesList = '<table id="allDishesTable" class="table table-bordered">';

            for (var i = 0; i < dishes.length; i++) {
                allDishesList += '<tr class="clickable-row" data-id="' + dishes[i].id + '"> <td>' + dishes[i].name + '</td></tr>'
            }

            allDishesList += '</table>';
            menuInfo.empty();
            menuInfo.append(allDishesList);

            $('#allDishesTable').on('click', '.clickable-row', function (event) {
                if ($(this).hasClass('active')) {
                    //$(this).removeClass('active');
                    //$('#menuInfo').empty();
                } else {
                    $(this).addClass('active').siblings().removeClass('active');
                    selectedDishId_add = $(this).data('id');

                    //getMenuDishes($(this).data('id'));
                    //alert(selectedDishId_add);
                }
            });
        }
    }

    function onAddMenuBtnClick() {
        var menuDetails = '' +
                '<div class="form-group"> ' +
                '<label class="control-label">Menu name:</label><input id="newMenuName" class="form-control" type="text"/> ' +
                '</div>' +
                '<div class="form-group"> ' +
                '<button class="btn btn-primary" type="submit" value="Add" onClick="addMenu()">Add</button> ' +
                '</div>' +
                '';
        $('#menuName').empty();
        $('#menuName').append(menuDetails);
    }
</script>
</body>
</html>
