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
        <div id="ingredientList" class=" text-right  col-md-6">
            <table class="table table-bordered" id="storageTable">
                <c:forEach items="${storageIngredients}" var="storageIngredient">
                    <tr data-id="${storageIngredient.id}" data-name="${storageIngredient.ingredient.title}"
                        data-quantity="${storageIngredient.quantity}"
                        class="clickable-row">
                        <td width="100%">${storageIngredient.ingredient.title}</td>
                        <td><input id="ingredient-quantity" type="text" value="${storageIngredient.quantity}"
                                   onchange="onQuantityChange(event)"/></td>
                        <td>
                            <a id="editQuantityLink" data-id="${storageIngredient.id}"
                               data-name="${storageIngredient.ingredient.title}" href="#"
                               class="edit-quantity">
                                <span class="glyphicon glyphicon-edit"></span>
                            </a>
                        </td>
                        <td>
                            <a id="deleteIngredientLink" data-id="${storageIngredient.id}"
                               data-name="${storageIngredient.ingredient.title}" href="#"
                               class="delete-ingredient">
                                <span class="glyphicon glyphicon-remove-circle"></span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="newIngredientInfo" class=" text-left col-md-6">
            <div class="row">
                <label>Add ingredient to the storage </label>
                <div class="form-group col-md-8">
                    <label class="control-label">Ingredient:</label>
                    <select class="form-control" id="ingredient">
                        <c:forEach items="${ingredients}" var="ingredient">
                            <option>${ingredient.title}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-md-8">
                    <label class="control-label">Quantity:</label>
                    <input id="quantity" title="Quantity:"
                           class="form-control" type="text" value="0"/></div>
            </div>
            <div class="text col-md-1 text-right">
                <button class="btn btn-primary" type="submit" value="Add" onClick="addIngredientToStorage()">Add
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    var newQuantity;

    jQuery(document).ready(function ($) {
        $('#storageTable').on('click', '.clickable-row', function (event) {
            if ($(this).hasClass('active')) {
            } else {
                $(this).addClass('active').siblings().removeClass('active');
            }
        });

        $('#storageTable').on('click', ".edit-quantity", function (event) {
            changeIngredientQuantity($(this).data('id'), newQuantity);
        });

        $('#storageTable').on('click', ".delete-ingredient", function (event) {
            deleteIngredientFromStorage($(this).data('id'));
        });
    });

    function onQuantityChange(event) {
        newQuantity = event.currentTarget.value;
    }

    function changeIngredientQuantity(storageIngredientId, quantity) {
        $.ajax({
            type: "PUT",
            url: "/storage/" + storageIngredientId + '/' + quantity,
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

    function deleteIngredientFromStorage(storageIngredientId) {
        $.ajax({
            type: "DELETE",
            url: "/storage/delete/" + storageIngredientId,
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

    function addIngredientToStorage() {
        var ingredientTitle = $("#ingredient")[0].value;
        var quantity = $("#quantity")[0].value;
        $.ajax({
            type: "PUT",
            url: "/storage/add/" + ingredientTitle + "/" + quantity,
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
