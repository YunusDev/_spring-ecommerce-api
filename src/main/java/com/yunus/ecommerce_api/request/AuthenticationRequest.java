package com.yunus.ecommerce_api.request;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class AuthenticationRequest implements Serializable {

    @NotEmpty(message = "userName must not be empty")
    private String userName;

    private String name;
    private String email;
    private String phone;

    @NotEmpty(message = "password must not be empty")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //need default constructor for JSON Parsing
    public AuthenticationRequest()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AuthenticationRequest(String userName,  String password) {
        this.userName = userName;
        this.password = password;
    }

    public AuthenticationRequest(String username, String name, String email, String phone, String password) {
        this.userName = username;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
