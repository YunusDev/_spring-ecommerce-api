package com.yunus.ecommerce_api.dao;


import com.yunus.ecommerce_api.EcommerceApiApplication;
import com.yunus.ecommerce_api.entity.User;
import com.yunus.ecommerce_api.service.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EcommerceApiApplication.class)
@Ignore
public class UserAuthTest {

//    @MockBean
//    private TestEntityManager testEntityManager;

    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser(){

        User user = new User("Kola", "Femi", "kola@gmail.com", "04890404", "password");

//        User userSaved = testEntityManager.persist(user);
         userService.save(user);

        System.out.println(">>>>> " + user);

        System.out.println(">>>>> +++++++ " + userService.findAll());

        User getSavedUser = userService.findUserByUsername(user.getUserName());

        System.out.println(">>>>> " + getSavedUser);

        int val = user.toString().compareTo(getSavedUser.toString());

//        assertEquals(val, 0);

        assertThat(getSavedUser.getId()).isEqualTo(1);
//        assertEquals(user, getSavedUser);

    }


}
