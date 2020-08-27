package com.yunus.ecommerce_api.service;

import com.yunus.ecommerce_api.dao.UserDao;
import com.yunus.ecommerce_api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByUsername(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findByEMail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userDao.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new  org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), Collections.emptyList());

    }

    @Override
    public void deleteAll() {

        userDao.deleteAll();

    }

    @Override
    public void save(User theUser) {

        System.out.println("user to save " + theUser);

        User user = new User();

        user.setName(theUser.getName());
        user.setUserName(theUser.getUserName());
        user.setEmail(theUser.getEmail());
        user.setPhone(theUser.getPhone());
        user.setPassword(bCryptPasswordEncoder.encode(theUser.getPassword()));

        userDao.save(user);

    }

    @Override
    public Long count() {
        return userDao.count();
    }

    @Override
    public void update(User user) {

        userDao.save(user);

    }

    @Override
    public List<User> findAll() {

        return  userDao.findAll();


    }
}
