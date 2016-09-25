package com.goit.gojavaonline.spring.mvc.dao.hibernate;


import com.goit.gojavaonline.spring.mvc.dao.OrderDao;
import com.goit.gojavaonline.spring.mvc.model.Employee;
import com.goit.gojavaonline.spring.mvc.model.Order;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tamila on 8/30/16.
 */
public class HOrderDao implements OrderDao{
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void save(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }

    @Override
    @Transactional
    public void insertDishInOrder(int orderId, int dishId) {

    }

    @Override
    @Transactional
    public void deleteDishFromOrder(int orderId, int dishId) {

    }

    @Override
    @Transactional
    public void deleteOrder(int id) {

    }

    @Override
    @Transactional
    public void closeOrder(int id) {

    }

    @Override
    @Transactional
    public List<Order> getOrders(boolean closed) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select o from Order o").list();
    }

    @Override
    @Transactional
    public Order getOrderById(int id) {
        Order result = sessionFactory.getCurrentSession().load(Order.class, id);
        if(result == null) {
            throw new RuntimeException("Cannot find Employee by id: "+ id);
        }
        return result;
    }

    @Override
    @Transactional
    public void removeAll() {
        sessionFactory.getCurrentSession().createQuery("delete from Order").executeUpdate();
    }

    @Override
    @Transactional
    public List<Order> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select o from Order o").list();
    }

    @Override
    @Transactional
    public List<Order> getByTable(int tableNum) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Order where o.tableNum = :tableNum");
        query.setParameter("tableNum", tableNum);

        return query.list();
    }

    @Override
    @Transactional
    public List<Order> getByWaiter(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Order where employee.id = :id");
        query.setParameter("id", id);

        List<Order> result = query.list();
        return result;
    }

    @Override
    @Transactional
    public List<Order> getByDate(String orderDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Order where orderDate = :orderDate");
        query.setParameter("orderDate", orderDate);

        return query.list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
