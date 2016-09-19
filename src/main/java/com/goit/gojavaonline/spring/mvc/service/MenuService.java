package com.goit.gojavaonline.spring.mvc.service;

import com.goit.gojavaonline.spring.mvc.dao.MenuDao;
import com.goit.gojavaonline.spring.mvc.model.Menu;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class MenuService {
    private MenuDao menuDao;

    @Transactional
    public List<Menu> getAllMenus() {
        return menuDao.getAll();
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }
}
