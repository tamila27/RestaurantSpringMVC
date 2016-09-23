package com.goit.gojavaonline.spring.mvc.dao;

import com.goit.gojavaonline.spring.mvc.model.DishIngredient;

import java.util.List;

/**
 * Created by tamila on 9/12/16.
 */
public interface DishIngredientDao {
    void save(DishIngredient dishIngredient);
    void delete(int ingredientId, int dishId);
    List<DishIngredient> getIngredientsByDishName(String dish);
    List<DishIngredient> getIngredientsByDishId(int id);

    void removeAll();
}
