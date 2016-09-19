package com.goit.gojavaonline.spring.mvc.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cook extends Employee{
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PREPARED_DISH",
            joinColumns = @JoinColumn(name = "COOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "ID")
    )
    @Fetch(FetchMode.JOIN)
    List<PreparedDish> coockedDishes;

    public List<PreparedDish> getCoockedDishes() {
        return coockedDishes;
    }

    public void setCoockedDishes(List<PreparedDish> coockedDishes) {
        this.coockedDishes = coockedDishes;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Cook{\n");
        sb.append("    id = ").append(getId()).append("\n");
        sb.append("    name = ").append(getName()).append("\n");
        sb.append("    lastname = ").append(getLastName()).append("\n");
        sb.append("    cooked_dishes = {\n");
        coockedDishes.forEach(prepared_dish -> sb.append("      ").append(prepared_dish).append("\n"));
        sb.append("    }\n");
        sb.append("}\n");
        return sb.toString();
    }
}
