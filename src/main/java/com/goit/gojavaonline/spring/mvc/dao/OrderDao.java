package com.goit.gojavaonline.spring.mvc.dao;

import com.goit.gojavaonline.spring.mvc.model.Order;

import java.util.List;

/**
 * Created by tamila on 8/23/16.
 */
public interface OrderDao {
    void save(Order order);
    void insertDishInOrder(int orderId, int dishId);
    void deleteDishFromOrder(int orderId, int dishId);
    void deleteOrder(int id);
    void closeOrder(int id);
    List<Order> getOrders(boolean closed);
    Order getOrderById(int id);
    void removeAll();
}
