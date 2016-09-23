package com.goit.gojavaonline.spring.mvc.dto;

import com.goit.gojavaonline.spring.mvc.model.Dish;
import com.goit.gojavaonline.spring.mvc.model.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tamila on 9/22/16.
 */
public class MenuDto {

    private int id;
    private String name;
    private List<DishDto> dishes;

    public static MenuDto convert(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setName(menu.getName());
        menuDto.setDishes(convertDishes( menu.getDishes()));

        return menuDto;
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

    public List<DishDto> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishDto> dishes) {
        this.dishes = dishes;
    }

    public static List<DishDto> convertDishes(List<Dish> dishesList) {
        List<DishDto> dishesDtoList = new ArrayList<>();

        if(dishesList != null){
            for (Dish d: dishesList) {
                DishDto dishDto = new DishDto();
                dishDto.setId(d.getId());
                dishDto.setName(d.getName());
                dishDto.setPrice(d.getPrice());
                dishDto.convertIngredients(d.getIngredients());

                dishesDtoList.add(dishDto);
            }
        }

        return dishesDtoList;
    }
}
