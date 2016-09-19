package com.goit.gojavaonline.spring.mvc.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DISHES_INGREDIENTS")
public class DishIngredient implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(name = "DISH_ID")
    private Dish dish;

    @Id
    @ManyToOne
    @JoinColumn(name = "INGREDIENT_ID")
    private Ingredient ingredient;

    @Column(name = "QUANTITY")
    private Integer quantity;

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "DishIngredient{" +
                "dish=" + dish +
                ", ingredient=" + ingredient +
                ", quantity=" + quantity +
                '}';
    }
}
