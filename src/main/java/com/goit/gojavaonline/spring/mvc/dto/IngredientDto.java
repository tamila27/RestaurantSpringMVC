package com.goit.gojavaonline.spring.mvc.dto;

import com.goit.gojavaonline.spring.mvc.model.Ingredient;

/**
 * Created by tamila on 9/21/16.
 */
public class IngredientDto {

    private int id;
    private String title;

    public static IngredientDto convert(Ingredient ingredient) {
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(ingredient.getId());
        ingredientDto.setTitle(ingredient.getTitle());
        return ingredientDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
