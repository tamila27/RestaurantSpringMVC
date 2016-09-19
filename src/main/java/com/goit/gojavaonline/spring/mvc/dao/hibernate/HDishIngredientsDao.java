package com.goit.gojavaonline.spring.mvc.dao.hibernate;

import com.goit.gojavaonline.spring.mvc.dao.DishIngredientDao;
import com.goit.gojavaonline.spring.mvc.model.DishIngredient;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tamila on 9/12/16.
 */
public class HDishIngredientsDao implements DishIngredientDao{
    private SessionFactory sessionFactory;
    @Override
    @Transactional
    public void save(DishIngredient dishIngredient) {
        sessionFactory.getCurrentSession().save(dishIngredient);
    }

    @Override
    @Transactional
    public void delete(int ingredientId, int dishId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DishIngredient di where di.ingredient.id = :ingredientId and di.dish.id = :dishId");
        query.setParameter("ingredientId", ingredientId);
        query.setParameter("dishId", dishId);
        DishIngredient dishIngredient = (DishIngredient) query.uniqueResult();
        sessionFactory.getCurrentSession().delete(dishIngredient);
    }

    @Override
    @Transactional
    public List<DishIngredient> getIngredientsByDishName(String dish) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from DishIngredient d where d.dish.name = :name");
        query.setParameter("name", dish);
        return query.list();
    }

    @Override
    @Transactional
    public void removeAll() {
        
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
