package com.goit.gojavaonline.spring.mvc.web;

import com.goit.gojavaonline.spring.mvc.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    private MenuService menuService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView menu() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("menuList", menuService.getAllMenus());
        modelAndView.setViewName("mainPage");
        return modelAndView;
    }

    @RequestMapping(value = "/tables", method = RequestMethod.GET)
    public ModelAndView tables() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tables");
        return modelAndView;
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ModelAndView contacts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contacts");
        return modelAndView;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}
