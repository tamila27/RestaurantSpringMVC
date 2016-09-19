package com.goit.gojavaonline.spring.mvc.dao;

import com.goit.gojavaonline.spring.mvc.model.Ingredient;

import java.util.List;

/**
 * Created by tamila on 9/9/16.
 */
public interface IngredientDao {
    void save(Ingredient ingredient);
    Ingredient loadByTitle(String title);
    Ingredient load(int id);
    List<Ingredient> getAll();
    void removeAll();
    void remove(Ingredient ingredient);
}
