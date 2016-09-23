package com.goit.gojavaonline.spring.mvc.service;

import com.goit.gojavaonline.spring.mvc.dao.DishDao;
import com.goit.gojavaonline.spring.mvc.dto.DishDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class DishService {
    private DishDao dishDao;

    @Transactional
    public DishDto getDishByName(String dishName) {
        return DishDto.convert(dishDao.findByName(dishName));
    }

    @Transactional
    public List<DishDto> getAllDishes() {
        return dishDao.findAll().stream().map(DishDto::convert).collect(Collectors.toList());
    }

    @Autowired
    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }
}
