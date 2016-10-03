package com.goit.gojavaonline.spring.mvc.web;

import com.goit.gojavaonline.spring.mvc.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController extends SecureController {
    private MenuService menuService;

    @RequestMapping( value = "/adminmenu", method = RequestMethod.GET)
    public ModelAndView menu() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("menus", menuService.getAllMenus());
        modelAndView.addObject("active", "menu");
        modelAndView.setViewName("adminMenu");
        return modelAndView;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}
