package com.yunus.ecommerce_api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yunus.ecommerce_api.validator.UniqueEmail;
import com.yunus.ecommerce_api.validator.UniqueUsername;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "name must not be empty")
    @Column(name = "name")
    private String name;

    @UniqueUsername
    @NotEmpty(message = "userName must not be empty")
    @Column(name = "username", unique = true)
    private String userName;

    @NotEmpty(message = "phone must not be empty")
    @Column(name = "phone")
    private String phone;

    @UniqueEmail
    @NotEmpty(message = "email must not be empty")
    @Email(message = "please provide a valid email")
    @Column(name = "email")
    private String email;

    @Column(name = "wallet_balance")
    private Integer walletBalance = 0;

    @NotEmpty(message = "password must not be empty")
    @Size(min = 6)
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    @JsonManagedReference(value = "user_card_back_ref")
    private List<Card> cards;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    @JsonManagedReference(value = "user_wallet_back_ref")
    private List<WalletLog> walletLogs;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    @JsonManagedReference(value = "user_order_back_ref")
    private List<Order> orders;


    public User(){


    }

    public User(Long id, @NotEmpty(message = "name must not be empty") String name, @NotEmpty(message = "userName must not be empty") String userName, @NotEmpty(message = "phone must not be empty") String phone, @NotEmpty(message = "email must not be empty") @Email(message = "please provide a valid email") String email, @NotEmpty(message = "password must not be empty") @Size(min = 6) String password) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public User(String name, String userName, String email, String phone, String password) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(Integer walletBalance) {
        this.walletBalance = walletBalance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<WalletLog> getWalletLogs() {
        return walletLogs;
    }

    public void setWalletLogs(List<WalletLog> walletLogs) {
        this.walletLogs = walletLogs;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
