package com.yunus.ecommerce_api.service;

import com.yunus.ecommerce_api.dao.OrderRepository;
import com.yunus.ecommerce_api.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void save(Order order) {

        orderRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {

        orderRepository.deleteById(id);

    }

    @Override
    public void deleteAll() {

         orderRepository.deleteAll();
    }
}
