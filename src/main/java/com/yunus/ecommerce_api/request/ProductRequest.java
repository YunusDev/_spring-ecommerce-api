package com.yunus.ecommerce_api.request;

import java.io.Serializable;

public class ProductRequest implements Serializable {

    private String name;

    private boolean status;

    private int quantity;

    private int price;

    private Long category_id;

    private String description;

    public ProductRequest() {
    }

    public ProductRequest(String name, boolean status, int quantity, int price, Long category_id, String description) {
        this.name = name;
        this.status = status;
        this.quantity = quantity;
        this.price = price;
        this.category_id = category_id;
        this.description = description;
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

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
