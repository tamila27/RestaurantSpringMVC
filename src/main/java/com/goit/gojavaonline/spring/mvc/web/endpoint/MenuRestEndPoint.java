package com.goit.gojavaonline.spring.mvc.web.endpoint;

import com.goit.gojavaonline.spring.mvc.dto.DishDto;
import com.goit.gojavaonline.spring.mvc.dto.MenuDto;
import com.goit.gojavaonline.spring.mvc.service.MenuService;
import com.goit.gojavaonline.spring.mvc.web.SecureController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tamila on 9/22/16.
 */
@RestController
public class MenuRestEndPoint extends SecureController {
    private MenuService menuService;

    @RequestMapping(value = "/menu/{menuId}", method = RequestMethod.GET)
    public List<DishDto> menu(@PathVariable(value = "menuId") String menuId) {
        return menuService.getDishes(menuId);
    }

    @RequestMapping(value = "/menu/edit/{menuId}/{dishId}", method = RequestMethod.PUT)
    public MenuDto addDish(@PathVariable(value = "menuId") String menuId,
                        @PathVariable(value = "dishId") String dishId) {
        menuService.addDish(Integer.valueOf(menuId), Integer.valueOf(dishId));
        return menuService.getMenuById(Integer.valueOf(menuId));
    }

    @RequestMapping(value = "/menu/dish/delete/{menuId}/{dishId}", method = RequestMethod.DELETE)
    public MenuDto deleteDish(@PathVariable(value = "menuId") String menuId,
                              @PathVariable(value = "dishId") String dishId) {
        menuService.deleteDish(Integer.valueOf(menuId), Integer.valueOf(dishId));
        return menuService.getMenuById(Integer.valueOf(menuId));
    }

    @RequestMapping(value = "/menu/add/{menuName}", method = RequestMethod.GET)
    public MenuDto addMenu(@PathVariable(value = "menuName") String menuName ) {
        return menuService.addMenu(menuName);
    }

    @RequestMapping(value = "/menu/delete/{menuId}", method = RequestMethod.DELETE)
    public MenuDto deleteMenu(@PathVariable(value = "menuId") String menuId) {
        menuService.deleteMenu(Integer.valueOf(menuId));
        return new MenuDto();
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}
