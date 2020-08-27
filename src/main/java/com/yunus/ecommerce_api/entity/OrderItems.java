package com.yunus.ecommerce_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "order_items")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price")
    private Integer unitPrice;

    @Column(name = "date")
    private Timestamp date;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    @JsonBackReference(value = "items_order_back_ref")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "items_prod_back_ref")
    private Product product;


    public OrderItems() {
    }

    public OrderItems(Integer quantity, Integer unitPrice, Timestamp date) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Timestamp getDate() {

        return date;
    }

    public void setDate(Timestamp date) {

        this.date = date;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", date=" + date +
//                ", order=" + order +
//                ", product=" + product +
                '}';
    }
}
