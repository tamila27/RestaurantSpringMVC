package com.goit.gojavaonline.spring.mvc.dao;

import com.goit.gojavaonline.spring.mvc.model.Ingredient;
import com.goit.gojavaonline.spring.mvc.model.IngredientsStorage;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tamila on 8/24/16.
 */
public interface StorageDao {
    void insertIngredientToStorage(IngredientsStorage ingredientsStorage);
    void deleteIngredientFromStorage(int id);
    void changeIngredientQuantity(int id, float newQuantity);
    IngredientsStorage getIngredientFromStorage(String name);
    List<IngredientsStorage> getAll();
    Ingredient getIngredientByName(String name);

    @Transactional
    IngredientsStorage getStorageIngredientById(int id);

    void removeAll();
}
