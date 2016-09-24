package com.goit.gojavaonline.spring.mvc.web.endpoint;

import com.goit.gojavaonline.spring.mvc.dto.IngredientsStorageDto;
import com.goit.gojavaonline.spring.mvc.model.Ingredient;
import com.goit.gojavaonline.spring.mvc.model.IngredientsStorage;
import com.goit.gojavaonline.spring.mvc.service.IngredientService;
import com.goit.gojavaonline.spring.mvc.service.StorageService;
import com.goit.gojavaonline.spring.mvc.web.SecureController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tamila on 9/24/16.
 */
@RestController
public class StorageEndpoint extends SecureController {
    private StorageService storageService;
    private IngredientService ingredientService;

    @RequestMapping(value = "/storage/{storageIngredientId}/{quantity}", method = RequestMethod.PUT)
    public IngredientsStorageDto changeIngredientQuantity(@PathVariable(value = "storageIngredientId") String storageIngredientId,
                                      @PathVariable(value = "quantity") String quantity) {
        storageService.changeIngredientQuantity(Integer.valueOf(storageIngredientId), Float.valueOf(quantity));
        return storageService.getStorageIngredientById(Integer.valueOf(storageIngredientId));
    }

    @RequestMapping(value = "/storage/delete/{storageIngredientId}", method = RequestMethod.DELETE)
    public IngredientsStorageDto deleteIngredientFromStorage(@PathVariable(value = "storageIngredientId") String storageIngredientId) {
        storageService.deleteIngredientFromStorage(Integer.valueOf(storageIngredientId));
        return new IngredientsStorageDto();
    }

    @RequestMapping(value = "/storage/add/{ingredientTitle}/{quantity}", method = RequestMethod.PUT)
    public IngredientsStorageDto insertIngredientToStorage(
                                                           @PathVariable(value = "ingredientTitle") String ingredientTitle,
                                                           @PathVariable(value = "quantity") String quantity) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientService.getIngredientByTitle(ingredientTitle).getId());
        IngredientsStorage ingrStorage = new IngredientsStorage();
        ingrStorage.setQuantity(Float.valueOf(quantity));
        ingrStorage.setIngredient(ingredient);

        storageService.insertIngredientToStorage(ingrStorage);
        return storageService.getStorageIngredientByName(ingredientTitle);
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
