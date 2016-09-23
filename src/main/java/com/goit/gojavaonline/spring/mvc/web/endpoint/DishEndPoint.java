package com.goit.gojavaonline.spring.mvc.web.endpoint;

import com.goit.gojavaonline.spring.mvc.dto.DishDto;
import com.goit.gojavaonline.spring.mvc.dto.DishIngredientDto;
import com.goit.gojavaonline.spring.mvc.model.DishCategory;
import com.goit.gojavaonline.spring.mvc.service.DishService;
import com.goit.gojavaonline.spring.mvc.web.SecureController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DishEndPoint extends SecureController {
    private DishService dishService;

    @RequestMapping(value = "/dish/{dishName}", method = RequestMethod.GET)
    public DishDto dish(@PathVariable("dishName") String dishName) {
        return dishService.getDishByName(dishName);
    }

    @RequestMapping(value = "/dish", method = RequestMethod.GET)
    public List<DishDto> getAllDishes() {
        return dishService.getAllDishes();
    }

    @RequestMapping(value = "/dish/add/{dishName}/{weight}/{price}", method = RequestMethod.PUT)
    public DishDto addDish(@PathVariable(value = "dishName") String dishName,
                           @PathVariable(value = "weight") String weight,
                           @PathVariable(value = "price") String price) {
        return dishService.addDish(dishName, Float.valueOf(weight), Float.valueOf(price), DishCategory.MAIN_DISH);
    }

    @RequestMapping(value = "/dish/ingredients/{dishId}", method = RequestMethod.GET)
    public List<DishIngredientDto> dishIngredients(@PathVariable(value = "dishId") String dishId) {
        return dishService.getIngredients(Integer.valueOf(dishId));
    }

    @RequestMapping(value = "/dish/edit/{dishId}/{ingredientId}/{quantity}", method = RequestMethod.PUT)
    public DishDto addIngredient(@PathVariable(value = "dishId") String dishId,
                                 @PathVariable(value = "ingredientId") String ingredientId,
                                 @PathVariable(value = "quantity") String quantity) {
        dishService.addIngredient(Integer.valueOf(dishId), Integer.valueOf(ingredientId), Integer.valueOf(quantity));
        return dishService.getDishById(Integer.valueOf(dishId));
    }

    @RequestMapping(value = "/dish/ingredient/delete/{dishId}/{ingredientId}", method = RequestMethod.DELETE)
    public DishDto deleteIngredient(@PathVariable(value = "dishId") String dishId,
                                    @PathVariable(value = "ingredientId") String ingredientId) {
        dishService.deleteIngredient(Integer.valueOf(dishId), Integer.valueOf(ingredientId));
        return dishService.getDishById(Integer.valueOf(dishId));
    }

    @RequestMapping(value = "/dish/delete/{dishId}", method = RequestMethod.DELETE)
    public DishDto deleteDish(@PathVariable(value = "dishId") String dishId) {
        dishService.deleteDish(Integer.valueOf(dishId));
        return new DishDto();
    }

    @RequestMapping(value = "/dish/update/{dishId}/{weight}/{price}", method = RequestMethod.PUT)
    public DishDto updateDish(@PathVariable(value = "dishId") String dishId,
                              @PathVariable(value = "weight") String weight,
                              @PathVariable(value = "price") String price) {
        dishService.updateDish(Integer.valueOf(dishId), Float.valueOf(weight), Float.valueOf(price));
        return dishService.getDishById(Integer.valueOf(dishId));
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }
}
