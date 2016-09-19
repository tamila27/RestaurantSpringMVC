package com.goit.gojavaonline.spring.mvc.dao.hibernate;

import com.goit.gojavaonline.spring.mvc.dao.MenuDao;
import com.goit.gojavaonline.spring.mvc.model.Dish;
import com.goit.gojavaonline.spring.mvc.model.Menu;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tamila on 8/31/16.
 */
public class HMenuDao implements MenuDao {
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void insertMenu(Menu menu) {
        sessionFactory.getCurrentSession().save(menu);
    }

    @Override
    @Transactional
    public void deleteMenu(int id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Override
    @Transactional
    public void insertDishInMenu(int menuId, Dish dish) {
        Menu menu = findById(menuId);
        menu.getDishes().add(dish);
        sessionFactory.getCurrentSession().update(menu);
    }

    @Override
    @Transactional
    public void deleteDishFromMenu(int menuId, Dish dish) {
        Menu menu = findById(menuId);
        menu.getDishes().remove(dish);
        sessionFactory.getCurrentSession().update(menu);
    }

    @Override
    @Transactional
    public Menu findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from Menu m where m.name like :name");
        query.setParameter("name", name);
        return (Menu) query.uniqueResult();
    }

    @Transactional
    public Menu findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from Menu m where m.id like :id");
        query.setParameter("id", id);
        return (Menu) query.uniqueResult();
    }

    @Override
    @Transactional
    public List<Menu> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select m from Menu m").list();
    }

    @Override
    @Transactional
    public List<Dish> getAllMenuDishes(int menuId) {
        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
