package com.goit.gojavaonline.spring.mvc.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
public class Waiter extends Employee {
    /*@OneToMany(*//*fetch = FetchType.LAZY*//*)
    @JoinColumn(name = "ID")
    @Fetch(FetchMode.JOIN)*/
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ORDERS",
            joinColumns = @JoinColumn(name = "WAITER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ID")
    )
    @Fetch(FetchMode.JOIN)
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Waiter{\n");
        sb.append("    id = ").append(getId()).append("\n");
        sb.append("    name = ").append(getName()).append("\n");
        sb.append("    lastname = ").append(getLastName()).append("\n");
        sb.append("    orders = {\n");
        orders.forEach(order -> sb.append("      ").append(order).append("\n"));
        sb.append("    }\n");
        sb.append("}\n");
        return sb.toString();
    }
}
