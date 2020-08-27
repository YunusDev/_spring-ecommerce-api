package com.yunus.ecommerce_api.service;

import com.yunus.ecommerce_api.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
public interface UserService extends UserDetailsService {

    public User findUserByUsername(String username);
    public User findUserByEmail(String email);
    public void save(User user);
    public List<User> findAll();
    public void update(User user);
    public void deleteAll();
    public Long count();


}
