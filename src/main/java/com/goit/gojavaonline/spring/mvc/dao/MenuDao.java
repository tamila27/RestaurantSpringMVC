package com.goit.gojavaonline.spring.mvc.dao;


import com.goit.gojavaonline.spring.mvc.model.Dish;
import com.goit.gojavaonline.spring.mvc.model.Menu;

import java.util.List;

/**
 * Created by tamila on 8/23/16.
 */
public interface MenuDao {
    void insertMenu(Menu menu);
    void deleteMenu(int id);
    void insertDishInMenu(int menuId, Dish dish);
    void deleteDishFromMenu(int menuId, Dish dish);
    Menu findByName(String name);
    List<Menu> getAll();
    List<Dish> getAllMenuDishes(int menuId);
}
