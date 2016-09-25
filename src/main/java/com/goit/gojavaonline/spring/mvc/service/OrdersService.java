package com.goit.gojavaonline.spring.mvc.service;

import com.goit.gojavaonline.spring.mvc.dao.OrderDao;
import com.goit.gojavaonline.spring.mvc.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class OrdersService {
    private OrderDao orderDao;

    @Transactional
    public List<OrderDto> getAllOrders() {
        return OrderDto.convert(orderDao.getAll());
    }

    @Transactional
    public List<OrderDto> getByTable(int tableNum) {
        return OrderDto.convert(orderDao.getByTable(tableNum));
    }

    @Transactional
    public List<OrderDto> getByWaiter(Long waiterId) {
        return OrderDto.convert(orderDao.getByWaiter(waiterId));
    }

    @Transactional
    public List<OrderDto> getByDate(String date) {
        return OrderDto.convert(orderDao.getByDate(date));
    }

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
