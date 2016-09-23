package com.goit.gojavaonline.spring.mvc.web.endpoint;

import com.goit.gojavaonline.spring.mvc.dto.IngredientDto;
import com.goit.gojavaonline.spring.mvc.service.IngredientService;
import com.goit.gojavaonline.spring.mvc.web.SecureController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tamila on 9/23/16.
 */
@RestController
public class IngredientEndPoint extends SecureController {
    private IngredientService ingredientService;

    @RequestMapping(value = "/ingredients", method = RequestMethod.GET)
    public List<IngredientDto> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @Autowired
    public void setIngredientService(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
}
