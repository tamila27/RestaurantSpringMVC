package com.goit.gojavaonline.spring.mvc.dto;

import com.goit.gojavaonline.spring.mvc.model.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {
    private int id;
    private EmployeeDto employee;
    private List<DishDto> dishes;
    private int tableNum;
    private String orderDate;
    private boolean closed;

    public static OrderDto convert(Order order) {
        OrderDto orderDto = new OrderDto();

        orderDto.setId(order.getId());
        orderDto.setEmployee(EmployeeDto.convert(order.getEmployee()));
        orderDto.setDishes(DishDto.convert(order.getDishes()));
        orderDto.setTableNum(order.getTableNum());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setClosed(order.isClosed());

        return orderDto;
    }

    public static List<OrderDto> convert(List<Order> orders) {
        return orders.stream().map(OrderDto::convert).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public List<DishDto> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishDto> dishes) {
        this.dishes = dishes;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
