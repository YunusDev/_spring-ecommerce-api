package com.yunus.ecommerce_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private boolean status;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private Date date;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    @JsonBackReference(value = "cat_prod_back_ref")
    private Category category;

    public Product() {
    }

    public Product(String name, boolean status, int quantity, int price, String description, Date date, Category category) {
        this.name = name;
        this.status = status;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.date = date;
        this.category = category;
    }

    public Product(Long id, String name, boolean status, int quantity, int price, String description, Date date, Category category) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.date = date;
        this.category = category;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", quantity=" + quantity +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", date=" + date +
//                ", category=" + category.getName() +
                '}';
    }
}
