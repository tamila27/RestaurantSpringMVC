package com.goit.gojavaonline.spring.mvc.web;

import com.goit.gojavaonline.spring.mvc.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DishController {

    private DishService dishService;

    private ModelAndView getDishModelAndView(String dishName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dish", dishService.getDishByName(dishName));
        modelAndView.setViewName("dish");
        return modelAndView;
    }

    @RequestMapping( value = "/admindish", method = RequestMethod.GET)
    public ModelAndView adminDish() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dishes", dishService.getAllDishes());
        modelAndView.addObject("active", "dish");
        modelAndView.setViewName("adminDish");
        return modelAndView;
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }
}
