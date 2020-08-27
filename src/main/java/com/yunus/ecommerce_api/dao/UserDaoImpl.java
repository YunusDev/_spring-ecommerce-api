package com.yunus.ecommerce_api.dao;

import com.yunus.ecommerce_api.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

    @Autowired
    EntityManager entityManager;

    @Override
    public User findByUserName(String username) {

        Session session = entityManager.unwrap(Session.class);

        Query<User> query = session.createQuery("from User where userName = :uName", User.class);

        query.setParameter("uName", username);

        User user = null;

        try{

            user = query.getSingleResult();

        }catch (Exception exception){

            user = null;
        }

        return user;
    }

    @Override
    public User findByEMail(String email) {

        Session session = entityManager.unwrap(Session.class);

        Query<User> query = session.createQuery("from User where email = :email", User.class);

        query.setParameter("email", email);

        User user = null;

        try{

            user = query.getSingleResult();

        }catch (Exception exception){

            user = null;
        }

        return user;
    }

    @Override
    public void save(User user) {

        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(user);

    }

    @Override
    public void deleteAll() {

        Session session = entityManager.unwrap(Session.class);

        session.createQuery("DELETE FROM User").executeUpdate();


    }

    @Override
    public Long count() {

        Session session = entityManager.unwrap(Session.class);

        Query<Long> query = session.createQuery("count(*) from User", Long.class);

        return query.getSingleResult();

    }

    @Override
    public List<User> findAll() {

        Session session = entityManager.unwrap(Session.class);

        List<User> users = new ArrayList<User>();


        Query<User> query = session.createQuery("from User", User.class);

        users = query.getResultList();

        return users;
    }
}
