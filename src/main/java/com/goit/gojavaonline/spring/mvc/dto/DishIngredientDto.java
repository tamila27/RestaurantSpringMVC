package com.goit.gojavaonline.spring.mvc.dto;

import com.goit.gojavaonline.spring.mvc.model.DishIngredient;

/**
 * Created by tamila on 9/21/16.
 */
public class DishIngredientDto {
    private IngredientDto ingredient;
    private Integer quantity;

    public static DishIngredientDto convert(DishIngredient dishIngredient) {
        DishIngredientDto dishIngredientDto = new DishIngredientDto();
        dishIngredientDto.setQuantity(dishIngredient.getQuantity());
        dishIngredientDto.setIngredient(IngredientDto.convert(dishIngredient.getIngredient()));
        return dishIngredientDto;
    }

    public IngredientDto getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientDto ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
