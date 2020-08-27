package com.yunus.ecommerce_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "user_addresses")
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "token")
    private String token;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "date")
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user_address_back_ref")
    private User user;

    @OneToMany(mappedBy = "userAddress", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JsonManagedReference(value = "order_add_back_ref")
    private List<Order> orders;

    public UserAddress() {
    }

    public UserAddress(Long id, String name, String token, Boolean isVerified, Timestamp date, User user) {
        this.id = id;
        this.name = name;
        this.token = token;
        this.isVerified = isVerified;
        this.date = date;
        this.user = user;
    }

    public UserAddress(String name, String token, Boolean isVerified, Timestamp date, User user) {
        this.name = name;
        this.token = token;
        this.isVerified = isVerified;
        this.date = date;
        this.user = user;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", isVerified=" + isVerified +
                ", date=" + date +
//                ", user=" + user +
//                ", orders=" + orders +
                '}';
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
