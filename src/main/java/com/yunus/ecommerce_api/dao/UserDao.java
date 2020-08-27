package com.yunus.ecommerce_api.dao;

import com.yunus.ecommerce_api.entity.User;

import java.util.List;

public interface UserDao   {

    public User findByUserName(String username);
    public User findByEMail(String email);
    public void save(User user);
    public List<User> findAll();
    public void deleteAll();
    public Long count();
}
