package com.goit.gojavaonline.spring.mvc.dto;

import com.goit.gojavaonline.spring.mvc.model.IngredientsStorage;

import java.util.ArrayList;
import java.util.List;

public class IngredientsStorageDto {
    private int id;
    private IngredientDto ingredient;
    private float quantity;

    public static IngredientsStorageDto convert(IngredientsStorage ingredientsStorage) {
        IngredientsStorageDto ingredientsStorageDto = new IngredientsStorageDto();
        ingredientsStorageDto.setId(ingredientsStorage.getId());
        ingredientsStorageDto.setIngredient(IngredientDto.convert(ingredientsStorage.getIngredient()));
        ingredientsStorageDto.setQuantity(ingredientsStorage.getQuantity());
        return ingredientsStorageDto;
    }

    public static List<IngredientsStorageDto> convertList(List<IngredientsStorage> list) {
        List<IngredientsStorageDto> result = new ArrayList<>();
        for (IngredientsStorage ingr : list) {
            result.add(IngredientsStorageDto.convert(ingr));
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IngredientDto getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientDto ingredient) {
        this.ingredient = ingredient;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}