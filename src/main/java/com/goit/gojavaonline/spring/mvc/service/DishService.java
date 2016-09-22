package com.goit.gojavaonline.spring.mvc.service;

import com.goit.gojavaonline.spring.mvc.dao.DishDao;
import com.goit.gojavaonline.spring.mvc.dto.DishDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class DishService {
    private DishDao dishDao;

    @Transactional
    public DishDto getDishByName(String dishName) {
        return DishDto.convert(dishDao.findByName(dishName));
    }

    @Autowired
    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }
}
