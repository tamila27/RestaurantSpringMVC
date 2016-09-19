package com.goit.gojavaonline.spring.mvc.dao.hibernate;

import com.goit.gojavaonline.spring.mvc.dao.PreparedDishDao;
import com.goit.gojavaonline.spring.mvc.model.PreparedDish;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tamila on 8/31/16.
 */
public class HPreparedDishDao implements PreparedDishDao {
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void insertPreparedDish(PreparedDish preparedDish) {
        sessionFactory.getCurrentSession().save(preparedDish);
    }

    @Override
    @Transactional
    public List<PreparedDish> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from PreparedDish").list();
    }
    @Override
    @Transactional
    public void removeAll() {
        sessionFactory.getCurrentSession().createQuery("delete from PreparedDish").executeUpdate();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
