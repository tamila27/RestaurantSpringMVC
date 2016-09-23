package com.goit.gojavaonline.spring.mvc.service;

import com.goit.gojavaonline.spring.mvc.dao.DishDao;
import com.goit.gojavaonline.spring.mvc.dao.DishIngredientDao;
import com.goit.gojavaonline.spring.mvc.dao.IngredientDao;
import com.goit.gojavaonline.spring.mvc.dto.DishDto;
import com.goit.gojavaonline.spring.mvc.dto.DishIngredientDto;
import com.goit.gojavaonline.spring.mvc.model.Dish;
import com.goit.gojavaonline.spring.mvc.model.DishCategory;
import com.goit.gojavaonline.spring.mvc.model.DishIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DishService {
    private DishDao dishDao;
    private IngredientDao ingredientDao;
    private DishIngredientDao dishIngredientDao;

    @Transactional
    public DishDto getDishByName(String dishName) {
        return DishDto.convert(dishDao.findByName(dishName));
    }

    @Transactional
    public List<DishDto> getAllDishes() {
        return dishDao.findAll().stream().map(DishDto::convert).collect(Collectors.toList());
    }

    @Transactional
    public DishDto addDish(String dishName, Float weight, Float price, DishCategory category) {
        Dish dish = new Dish();
        dish.setName(dishName);
        dish.setPrice(price);
        dish.setWeight(weight);
        dish.setCategory(category);
        dishDao.save(dish);

        return DishDto.convert(dishDao.findByName(dishName));
    }

    @Transactional
    public List<DishIngredientDto> getIngredients(int dishId) {
        List<DishIngredientDto> result = new ArrayList<>();
        for (DishIngredient ingredient : dishIngredientDao.getIngredientsByDishId(dishId)) {
            result.add(DishIngredientDto.convert(ingredient));
        }
        return result;
    }

    @Transactional
    public void addIngredient(int dishId, int ingredientId, int quantity) {
        DishIngredient dishIngredient = new DishIngredient();
        dishIngredient.setDish(dishDao.findById(dishId));
        dishIngredient.setQuantity(quantity);
        dishIngredient.setIngredient(ingredientDao.load(ingredientId));
        dishIngredientDao.save(dishIngredient);
    }

    @Transactional
    public void deleteIngredient(int dishId, int ingredientId) {
        dishIngredientDao.delete(ingredientId, dishId);
    }

    @Transactional
    public DishDto getDishById(int dishId) {
        return DishDto.convert(dishDao.findById(dishId));
    }

    @Transactional
    public void deleteDish(int dishId) {
        dishDao.deleteDish(dishDao.findById(dishId));
    }

    @Transactional
    public void updateDish(int dishId, float weight, float price) {
        dishDao.updateDish(dishId, weight, price);
    }

    @Autowired
    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Autowired
    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Autowired
    public void setDishIngredientDao(DishIngredientDao dishIngredientDao) {
        this.dishIngredientDao = dishIngredientDao;

    }
}
