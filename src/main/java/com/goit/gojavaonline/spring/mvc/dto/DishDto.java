package com.goit.gojavaonline.spring.mvc.dto;

import com.goit.gojavaonline.spring.mvc.model.Dish;
import com.goit.gojavaonline.spring.mvc.model.DishCategory;
import com.goit.gojavaonline.spring.mvc.model.DishIngredient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DishDto {
    private int id;
    private String name;
    private DishCategory category;
    private float price;
    private float weight;
    private List<DishIngredientDto> ingredients;

    public static DishDto convert(Dish dish) {
        DishDto dishDto = new DishDto();
        dishDto.setId(dish.getId());
        dishDto.setWeight(dish.getWeight());
        dishDto.setPrice(dish.getPrice());
        dishDto.setName(dish.getName());
        dishDto.setCategory(dish.getCategory());
        dishDto.convertIngredients(dish.getIngredients());
        return dishDto;
    }

    public void setIngredients(List<DishIngredientDto> ingredients) {
        this.ingredients = ingredients;
    }

    public void convertIngredients(List<DishIngredient> ingredients) {
        this.ingredients = new ArrayList<>();
        if(ingredients != null) {
            for (int i = 0; i < ingredients.size(); i++) {
                this.ingredients.add(DishIngredientDto.convert(ingredients.get(i)));
            }
        }
    }

    public static List<DishDto> convert(List<Dish> dishes) {
        return dishes.stream().map(DishDto::convert).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishCategory getCategory() {
        return category;
    }

    public void setCategory(DishCategory category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public List<DishIngredientDto> getIngredients() {
        return ingredients;
    }
}
