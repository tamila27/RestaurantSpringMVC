package com.goit.gojavaonline.spring.mvc.dao.hibernate;

import com.goit.gojavaonline.spring.mvc.dao.IngredientDao;
import com.goit.gojavaonline.spring.mvc.model.Ingredient;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tamila on 9/9/16.
 */
public class HIngredientDao implements IngredientDao {
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void save(Ingredient ingredient) {
        sessionFactory.getCurrentSession().save(ingredient);
    }

    @Override
    @Transactional(readOnly = true)
    public Ingredient loadByTitle(String title){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Ingredient e where e.title like :title");
        query.setParameter("title", title);

        return (Ingredient) query.uniqueResult();
    }

    @Override
    @Transactional
    public Ingredient load(int id) {
        Ingredient result = sessionFactory.getCurrentSession().load(Ingredient.class, id);
        if(result == null) {
            throw new RuntimeException("Cannot find Ingredient by id: "+ id);
        }
        return result;
    }

    @Override
    @Transactional
    public List<Ingredient> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select e from Ingredient e").list();
    }

    @Override
    @Transactional
    public void removeAll() {
        sessionFactory.getCurrentSession().createQuery("delete from Ingredient").executeUpdate();

    }

    @Override
    @Transactional
    public void remove(Ingredient ingredient) {
        sessionFactory.getCurrentSession().delete(ingredient);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
