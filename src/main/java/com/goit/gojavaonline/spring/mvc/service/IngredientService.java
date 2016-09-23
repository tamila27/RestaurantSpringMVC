package com.goit.gojavaonline.spring.mvc.service;

import com.goit.gojavaonline.spring.mvc.dao.IngredientDao;
import com.goit.gojavaonline.spring.mvc.dto.IngredientDto;
import com.goit.gojavaonline.spring.mvc.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tamila on 9/23/16.
 */
public class IngredientService {
    private IngredientDao ingredientDao;

    @Transactional
    public List<IngredientDto> getAllIngredients() {
        List<IngredientDto> result = new ArrayList<>();
        for (Ingredient ingredient : ingredientDao.getAll()) {
            result.add(IngredientDto.convert(ingredient));
        }
        return result;
    }

    @Autowired
    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }
}
