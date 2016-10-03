package com.goit.gojavaonline.spring.mvc.web;

import com.goit.gojavaonline.spring.mvc.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrdersController extends SecureController{
    private OrdersService ordersService;

    @RequestMapping( value = "/adminorder", method = RequestMethod.GET)
    public ModelAndView adminDish() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders", ordersService.getAllOrders());
        modelAndView.addObject("active", "order");
        modelAndView.setViewName("adminOrder");
        return modelAndView;
    }

    @Autowired
    public void setOrdersService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }
}
