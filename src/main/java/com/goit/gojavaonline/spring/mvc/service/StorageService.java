package com.goit.gojavaonline.spring.mvc.service;

import com.goit.gojavaonline.spring.mvc.dao.StorageDao;
import com.goit.gojavaonline.spring.mvc.dto.IngredientsStorageDto;
import com.goit.gojavaonline.spring.mvc.model.IngredientsStorage;
import com.goit.gojavaonline.spring.mvc.web.AuthenticationRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class StorageService {
    private StorageDao storageDao;

    @Transactional
    public List<IngredientsStorageDto> getAllIngredients() {
        return IngredientsStorageDto.convertList(storageDao.getAll());
    }

    @AuthenticationRequired
    @Transactional
    public void changeIngredientQuantity(int storageIngredientId, float quantity) {
        storageDao.changeIngredientQuantity(storageIngredientId, quantity);
    }

    @Transactional
    public IngredientsStorageDto getStorageIngredientById(int storageIngredientId) {
        return IngredientsStorageDto.convert(storageDao.getStorageIngredientById(storageIngredientId));
    }

    @AuthenticationRequired
    @Transactional
    public void deleteIngredientFromStorage(int ingredientId) {
        storageDao.deleteIngredientFromStorage(ingredientId);
    }

    @AuthenticationRequired
    @Transactional
    public void insertIngredientToStorage(IngredientsStorage ingredientsStorage) {
        storageDao.insertIngredientToStorage(ingredientsStorage);
    }

    @Transactional
    public IngredientsStorageDto getStorageIngredientByName(String ingredientTitle) {
        return IngredientsStorageDto.convert(storageDao.getIngredientFromStorage(ingredientTitle));

    }

    @Autowired
    public void setStorageDao(StorageDao storageDao) {
        this.storageDao = storageDao;
    }
}
