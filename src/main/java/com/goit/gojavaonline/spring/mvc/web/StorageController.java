package com.goit.gojavaonline.spring.mvc.web;

import com.goit.gojavaonline.spring.mvc.service.IngredientService;
import com.goit.gojavaonline.spring.mvc.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class StorageController extends SecureController {
    private StorageService storageService;
    private IngredientService ingredientService;

    @RequestMapping( value = "/adminstorage", method = RequestMethod.GET)
    public ModelAndView adminDish() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ingredients", ingredientService.getAllIngredients());
        modelAndView.addObject("storageIngredients", storageService.getAllIngredients());
        modelAndView.addObject("active", "storage");
        modelAndView.setViewName("adminStorage");
        return modelAndView;
    }

    @Autowired
    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired

    public void setIngredientService(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
}
