package com.yunus.ecommerce_api.validator;

import com.yunus.ecommerce_api.entity.User;
import com.yunus.ecommerce_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {


        try{

            User user = userService.findUserByUsername(username);

            return user == null;

        }catch (Exception e){

            return true;

        }
    }
}
