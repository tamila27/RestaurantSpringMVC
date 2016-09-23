package com.goit.gojavaonline.spring.mvc.service;

import com.goit.gojavaonline.spring.mvc.dao.DishDao;
import com.goit.gojavaonline.spring.mvc.dao.MenuDao;
import com.goit.gojavaonline.spring.mvc.dto.DishDto;
import com.goit.gojavaonline.spring.mvc.dto.MenuDto;
import com.goit.gojavaonline.spring.mvc.model.Dish;
import com.goit.gojavaonline.spring.mvc.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


public class MenuService {
    private MenuDao menuDao;
    private DishDao dishDao;

    @Transactional
    public List<MenuDto> getAllMenus() {
        List<MenuDto> menuDtoList = new ArrayList<>();
        List<Menu> menues = menuDao.getAll();
        for (Menu m : menues) {
            MenuDto menuDto = MenuDto.convert(m);
            menuDtoList.add(menuDto);
        }
        return menuDtoList;
    }

    @Transactional
    public List<DishDto> getDishes(String menuId) {
        Menu menu = menuDao.findById(Integer.valueOf(menuId));
        List<DishDto> result = new ArrayList<>();
        for(Dish dish : menu.getDishes()) {
            result.add(DishDto.convert(dish));
        }
        return result;
    }

    @Transactional
    public void addDish(int menuId, int dishId) {
        menuDao.insertDishInMenu(menuId, dishDao.findById(dishId));
    }

    @Transactional
    public void deleteDish(int menuId, int dishId) {
        menuDao.deleteDishFromMenu(menuId, dishDao.findById(dishId));
    }

    @Transactional
    public MenuDto getMenuById(int menuId) {
        return MenuDto.convert(menuDao.findById(menuId));
    }

    @Transactional
    public MenuDto addMenu(String menuName) {
        Menu menu = new Menu();
        menu.setName(menuName);
        menuDao.insertMenu(menu);

        return MenuDto.convert(menuDao.findByName(menuName));
    }

    @Transactional
    public void deleteMenu(int menuId) {
        menuDao.deleteMenu(menuId);
    }

    @Autowired
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Autowired
    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }
}
