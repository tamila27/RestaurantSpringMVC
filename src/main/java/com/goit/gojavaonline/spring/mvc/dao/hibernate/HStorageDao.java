package com.goit.gojavaonline.spring.mvc.dao.hibernate;

import com.goit.gojavaonline.spring.mvc.dao.StorageDao;
import com.goit.gojavaonline.spring.mvc.model.Ingredient;
import com.goit.gojavaonline.spring.mvc.model.IngredientsStorage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by tamila on 8/31/16.
 */
public class HStorageDao implements StorageDao {
    private SessionFactory sessionFactory;

    @Override
    public void insertIngredientToStorage(IngredientsStorage ingredientsStorage) {
        sessionFactory.getCurrentSession().save(ingredientsStorage);
    }

    @Override
    public void deleteIngredientFromStorage(int id) {
        sessionFactory.getCurrentSession().delete(getStorageIngredientById(id));
    }

    @Override
    public void changeIngredientQuantity(int id, float newQuantity) {
        IngredientsStorage ingredientsStorage = getStorageIngredientById(id);
        ingredientsStorage.setQuantity(newQuantity);
        sessionFactory.getCurrentSession().update(ingredientsStorage);
    }

    @Override
    public IngredientsStorage getIngredientFromStorage(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from IngredientsStorage i where i.ingredient.title like :title");
        query.setParameter("title", name);
        return (IngredientsStorage) query.uniqueResult();
    }

    @Override
    public List<IngredientsStorage> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select i from IngredientsStorage i").list();
    }

    @Override
    public Ingredient getIngredientByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Ingredient i where i.title like :title");
        query.setParameter("title", name);
        return (Ingredient) query.uniqueResult();
    }

    @Override
    public void removeAll() {
        sessionFactory.getCurrentSession().createQuery("delete from IngredientsStorage").executeUpdate();
    }

    private IngredientsStorage getStorageIngredientById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from IngredientsStorage i where i.id like :id");
        query.setParameter("id", id);
        return (IngredientsStorage) query.uniqueResult();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
