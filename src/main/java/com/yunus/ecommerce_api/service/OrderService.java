package com.yunus.ecommerce_api.service;

import com.yunus.ecommerce_api.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    public List<Order> findAll();
    public Optional<Order> findById(Long id);
    public void save(Order order);
    public void deleteById(Long id);
    public void deleteAll();
}
