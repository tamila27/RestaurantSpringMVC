package com.goit.gojavaonline.spring.mvc.dao;

import com.goit.gojavaonline.spring.mvc.model.PreparedDish;

import java.util.List;

/**
 * Created by tamila on 8/24/16.
 */
public interface PreparedDishDao {
    void insertPreparedDish(PreparedDish preparedDish);
    List<PreparedDish> getAll();
    void removeAll();
}
