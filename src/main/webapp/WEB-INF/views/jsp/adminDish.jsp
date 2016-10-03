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
        <div id="dishesInfo" class=" text-right  col-md-6">
            <div class="row container text-right">
                <div class="row col-md-5">
                    <input id="newDishName" class="form-control" type="text"/>
                </div>
                <div class="col-md-1">
                    <button class="btn btn-primary" type="submit" value="Add" onClick="addDish()">Add</button>
                </div>
                <br/> <br/><br/>
            </div>

            <table class="table table-bordered" id="dishesTable">
                <c:forEach items="${dishes}" var="dish">
                    <tr data-id="${dish.id}" data-name="${dish.name}" data-weight="${dish.weight}" data-price="${dish.price}" class="clickable-row">
                        <td width="100%">${dish.name}</td>
                        <td>
                            <a id="editDishLink" data-id="${dish.id}" data-name="${dish.name}" href="#"
                               class="edit-dish">
                                <span class="glyphicon glyphicon-edit"></span>
                            </a>
                        </td>
                        <td>
                            <a id="deleteDishLink" data-id="${dish.id}" data-name="${dish.name}" href="#"
                               class="delete-dish">
                                <span class="glyphicon glyphicon-remove-circle"></span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div id="dishInfo" class=" text-left col-md-6" hidden="true">
            <div id="dishName"></div>

            <div class="row container text-right">
                <div class="row col-md-5 text-right">
                    <div class="row col-md-3 text-right">Weight:<input id="dishWeight" title="Weight:" class="form-control" type="text" value="0"/></div>
                    <div class="row col-md-3 text-right">Price: <input id="dishPrice" title="Price:" class="form-control" type="text" value="0"/></div>
                </div>
                <div class="col-md-1">
                    <button class="btn btn-primary" type="submit" value="Update" onClick="updateDish()">Update</button>
                </div>

            </div>
            <br/>
            <div class="row">
                <div id="dishIngredients" class="col-md-5"></div>

                <div height="100%" id="editIngredientsButtons" class="col-md-2 text-center">
                    <p id="addIngredientLink" data-id="${dish.id}" data-name="${dish.name}" href="#">
                        <span class="glyphicon glyphicon-menu-left"></span>
                    </p>
                    <br/>
                    <br/>
                    <p id="deleteIngredientLink" data-id="${dish.id}" data-name="${dish.name}" href="#">
                        <span class="glyphicon glyphicon-menu-right"></span>
                    </p>
                </div>

                <div id="allIngredients" class="col-md-5"></div>
            </div>
        </div>
    </div>

</div>
<script>
    var selectedDishId;
    var selectedIngredientId_add;
    var selectedIngredientId_delete;
    var selectedIngredientQuantity = 0;

    jQuery(document).ready(function ($) {

        getAllIngredients();

        $('#dishesTable').on('click', '.clickable-row', function (event) {
            if ($(this).hasClass('active')) {
                //$(this).removeClass('active');
                //$('#menuInfo').empty();
            } else {
                $(this).addClass('active').siblings().removeClass('active');
                selectedDishId = $(this).data('id');
                $('#dishInfo').show();
                var dishDetails = '<h2>' + $(this).data('name') + '</h2>';
                $('#dishName').empty();
                $('#dishName').append(dishDetails);
                getDishIngredients($(this).data('id'));
                $('#dishWeight').val($(this).data('weight'));
                $('#dishPrice').val($(this).data('price'));
                //alert($(this).data('id'));
            }
        });

        $('#addIngredientLink').click(function (event) {
            addIngredientToDish(selectedDishId, selectedIngredientId_add, selectedIngredientQuantity);

        });

        $('#deleteIngredientLink').click(function (event) {
            deleteIngredientFromDish(selectedDishId, selectedIngredientId_delete);

        });

        $('#dishesTable').on('click', ".edit-dish", function (event) {
            getAllIngredients();

        });

        $('#dishesTable').on('click', ".delete-dish", function (event) {
            $.ajax({
                type: "DELETE",
                url: "/dish/delete/" + selectedDishId,
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
        });
    });

    function getAllIngredients() {

        $.ajax({
            type: "GET",
            url: "/ingredients",
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                buildAllIngredientsList(data);
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        });
    }

    function getDishIngredients(dishId) {

        $.ajax({
            type: "GET",
            url: "/dish/ingredients/" + dishId,
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                buildDishIngredientsList(data);
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        });
    }

    function addIngredientToDish(dishId, ingredientId, quantity) {

        $.ajax({
            type: "PUT",
            url: "/dish/edit/" + dishId + "/" + ingredientId + "/" + quantity,
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                getDishIngredients(selectedDishId);
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        });
    }

    function deleteIngredientFromDish(dishId, ingredientId) {

        $.ajax({
            type: "DELETE",
            url: "/dish/ingredient/delete/" + dishId + "/" + ingredientId,
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ", data);
                getDishIngredients(selectedDishId);
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        });
    }

    function addDish() {
        var dishName = $('#newDishName')[0].value;
        var dishWeight = $('#dishWeight')[0].value;
        var dishPrice = $('#dishPrice')[0].value;
        $.ajax({
            type: "PUT",
            url: "/dish/add/" + dishName+"/"+dishWeight+"/"+dishPrice,
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

    function buildDishIngredientsList(ingredients) {
        var dishIngredientsInfo = $('#dishIngredients');
        if (dishIngredientsInfo != null && dishIngredientsInfo != undefined) {

            var dishInfoHtml = '<table id="dishIngredientsTable" class="table table-bordered">';
            for (var i = 0; i < ingredients.length; i++) {
                dishInfoHtml += '<tr class="clickable-row" data-id="' + ingredients[i].ingredient.id + '"> <td>' + ingredients[i].ingredient.title + '</td></tr>';
            }
            dishInfoHtml += '</table>';
            dishIngredientsInfo.empty();
            dishIngredientsInfo.append(dishInfoHtml);

            $('#dishIngredientsTable').on('click', '.clickable-row', function (event) {
                if ($(this).hasClass('active')) {
                    //$(this).removeClass('active');
                    //$('#menuInfo').empty();
                } else {
                    $(this).addClass('active').siblings().removeClass('active');
                    selectedIngredientId_delete = $(this).data('id');

                    //getMenuDishes($(this).data('id'));
                    //alert(selectedDishId_delete);
                }
            });

        }
    }

    function buildAllIngredientsList(ingredients) {
        var dishIngredientsInfo = $('#allIngredients');
        if (dishIngredientsInfo != null && dishIngredientsInfo != undefined) {
            var allIngredientsList = '<table id="allIngredientsTable" class="table table-bordered">';

            for (var i = 0; i < ingredients.length; i++) {
                allIngredientsList += '<tr class="clickable-row" data-id="' + ingredients[i].id + '"> <td>' + ingredients[i].title + '</td></tr>'
            }

            allIngredientsList += '</table>';
            dishIngredientsInfo.empty();
            dishIngredientsInfo.append(allIngredientsList);

            $('#allIngredientsTable').on('click', '.clickable-row', function (event) {
                if ($(this).hasClass('active')) {
                    //$(this).removeClass('active');
                    //$('#menuInfo').empty();
                } else {
                    $(this).addClass('active').siblings().removeClass('active');
                    selectedIngredientId_add = $(this).data('id');

                    //getMenuDishes($(this).data('id'));
                    //alert(selectedDishId_add);
                }
            });
        }
    }

    function onAddDishBtnClick() {
        var dishDetails = '' +
                '<div class="form-group"> ' +
                '<label class="control-label">Dish name:</label><input id="newDishName" class="form-control" type="text"/> ' +
                '</div>' +
                '<div class="form-group"> ' +
                '<button class="btn btn-primary" type="submit" value="Add" onClick="addDish()">Add</button> ' +
                '</div>' +
                '';
        $('#dishName').empty();
        $('#dishName').append(dishDetails);
    }

    function updateDish() {
        var dishId = selectedDishId;
        var dishWeight = $('#dishWeight')[0].value;
        var dishPrice = $('#dishPrice')[0].value;
        $.ajax({
            type: "PUT",
            url: "/dish/update/" + dishId+"/"+dishWeight+"/"+dishPrice,
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
