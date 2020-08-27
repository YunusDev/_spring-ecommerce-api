package com.yunus.ecommerce_api.response;

import com.yunus.ecommerce_api.entity.User;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private final String jwt;
    private final User user;

    public AuthenticationResponse(String jwt, User user) {
        this.jwt = jwt;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getJwt() {
        return jwt;
    }
}
